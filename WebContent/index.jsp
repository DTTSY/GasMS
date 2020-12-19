<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<title>系统主界面</title>
<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin.css">
<!-- <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/elmemt_index.css"> -->
<script src="${pageContext.request.contextPath}/static/js/lib/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/lib/vue.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/lib/elementUI.js"></script>
<script src="${pageContext.request.contextPath}/static/js/lib/sweetalert2@10.js"></script>
<script src="http://cdn.staticfile.org/moment.js/2.24.0/moment.js"></script>
<script src="https://cdn.jsdelivr.net/npm/echarts@5.0.0/dist/echarts.min.js"></script>
 
</head>
<body>
<div id="app">
	<template>
	<el-container>
		<el-header style="width: 100%;" height="40px"></el-header>
		<el-container>
			<el-aside style="height: 100%;" width="200px">
				<el-menu mode="vertical">
					<el-menu-item index="1" @click.native.prevent="()=>{showTable=false;showChart=false;}">首页</el-menu-item>
					<el-submenu index="2">
						<span  slot="title">系统信息</span>
						<el-menu-item index="2-0" @click.native.prevent="showShelt('getAllGasFields', 0)">气田信息管理</el-menu-item>
					</el-submenu>
					<el-menu-item index="3" @click.native.prevent="Initchart">天然气数据汇总</el-menu-item>
				</el-menu>
			</el-aside>

				<el-main>
					<el-card v-show="showTable">
						<el-row :gutter="20">
							<el-col :span="6">
								<span>增加</span>
								<el-button  type="primary" icon="el-icon-circle-plus-outline" circle @click.native.prevent="addData"></el-button>
							</el-col>
							<el-col :span="6">
								<el-select v-model="area" placeholder="请选择需要浏览的气区">
									<el-option
									v-for="item in areaOptions"
									:key="item.value"
									:label="item.label"
									:value="item.value">
									</el-option>
								</el-select>
							</el-col>

							<el-col :span="6">
								<el-button  type="primary" icon="el-icon-search" circle @click.native.prevent="showShelt('getAllGasfieldByArea', 1)"></el-button>
							</el-col>

							
						</el-row>

						<el-row :gutter="20"></el-row>
							<div>
								<el-col :span="6" v-for="(Value, name) in this.gasField" :key="name"><el-input  v-model="gasField[name]" :placeholder="name" clearable></el-input></div></el-col>
							</div>
						</el-row>

						<el-table  v-loading="loading" :data="tableList.slice((currentPage-1)*pageSize,currentPage*pageSize)" :fit="true" :show-header="true" stripe="true">
							<el-table-column v-for="(value,name) in this.tableListCol" :prop="name" :label="value"></el-table-column>
							<el-table-column label="操作" fixed="right" width="200px">
								<template slot-scope="scope">
								<el-button mc-type="column-el-button" size="mini" type="primary" @click.native.prevent="handleModify(scope.$index, scope.row)" icon="el-icon-edit" round>编辑</el-button>
								<el-button mc-type="column-el-button" size="mini" type="danger" @click.native.prevent="handleDelete(scope.$index, scope.row)" icon="el-icon-delete" round>删除</el-button>

								</template>
							</el-table-column>
						</el-table>
						<el-pagination style="margin-left: -5px;" @current-change="handleCurrentChange" layout="prev,pager,next" :total="tableList.length" :current-page.sync="currentPage" :page-size="pageSize" :pager-count="5"></el-pagination>
					</el-card>

					<el-card v-show="showChart">
					  <el-row :gutter="20">
						<el-col :span="6">
							<el-select v-model="types" placeholder="请选择气藏类型">
								<el-option
								v-for="item in typeOptions"
								:key="item.value"
								:label="item.label"
								:value="item.value">
								</el-option>
							</el-select>
						</el-col>

						<el-col :span="6">
							<el-button  type="primary" icon="el-icon-search" circle @click.native.prevent="showInfo(0)"></el-button><span>气藏类型</span>
						</el-col>

						<el-col :span="6">
							<el-select v-model="depthType" placeholder="请选择或埋深类型">
								<el-option
								v-for="item in depthTypeOptions"
								:key="item.value"
								:label="item.label"
								:value="item.value">
								</el-option>
							</el-select>
						</el-col>

						<el-col :span="6">
							<el-button  type="primary" icon="el-icon-search" circle @click.native.prevent="showInfo(1)"></el-button><span>埋深类型</span>
						</el-col>

					  </el-row>

					  <el-row :gutter="20">
						<el-col :span="6" :offset="5">
							<div id="chart1" style="width: 400px;height: 400px;">
							</div>
						</el-col>

						<el-col :span="6">
							<h1>天然气总储量：{{total+" 亿方"}}</h1>
						</el-col>

					  </el-row>
					  </el-card>


					<el-dialog title="修改信息" :visible.sync="this.dialogFormModify">
						<div>
							<el-form :model="editItem">
						  		<el-form-item  v-for="(Value, name) in editItem" :label="name" :key="name">
									<el-input  v-model="editItem[name]"></el-input>
						  		</el-form-item>
							</el-form>
							<!-- <el-input v-for="item in this.car" :v-model="item"></el-input> -->
						</div>

						<div slot="footer" class="dialog-footer">
						  <el-button @click="dialogFormModify = false">取 消</el-button>
						  <el-button type="primary" @click.native.prevent="sbmittModify">确 定</el-button>
						</div>
					  </el-dialog>
				</el-main>

		</el-container>
	</el-container>
	</template>
</div>

<script src="${pageContext.request.contextPath}/static/js/admin.js"></script>

</body>

</html>

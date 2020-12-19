//调试:打开浏览器控制台(F12),在代码中某行增加 debugger 即可调试

let vueData = function () {
    return {
        tableNo: 0,
       gasField:{
    //    "气田编号": '',
       "气田名": '',
       "气区": '',
       "气藏类型": '',
       "埋深类型": '',
       "储量": ''
        },
        area:'',
        types: '',
        depthType:'',
        areaOptions:'',
        typeOptions:'',
        depthTypeOptions: '',
        type_data:false,
        total: 0,
        editItem: "",
        itemId:'',
        itemIndex:'',
        dialogFormModify: false,
        tableListCol: {},
        tableList : [],
        currentPage : 1,
        radioGroup : "",
        pageSize: 9,
        checkboxGroup : [],
        input : "",
        select : "",
        badge : 10,
        showTable: false,
        showUserGUI:false,
        slider : 0,
        fileList : [],
        loading: true,
        showChart: false
}
}

let Ctor = Vue.extend({
    //绑定的变量
    data: vueData,
    /*当前页面绑定的方法集合 与布局器节点一一映射即可 参照element ui文档*/
    methods: {
        handleDelete(index, row){
            let _t = this;
            Swal.fire({
                title: '确认删除?',
                text: "此操作不能恢复！",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#d33',
                cancelButtonColor: '#3085d6',
                confirmButtonText: '删除!',
                cancelButtonText: '取消!'
              }).then((result) => {
                if (result.isConfirmed) {
                    let data={
                        operation: 'deleteGasField',
                        id: row.气田编号
                    }
                    $.ajax({
                        url: "GFMSService.do",
                        data: data,
                        type: "GET",
                        dataType: "json",
                        success: function(data) {
                            if(data.ok == 1){
                                Swal.fire({
                                    icon: 'success',
                                    title: '(๑•̀ㅂ•́)و✧',
                                    text: '删除数据成功'
                                    });
                                //console.log(data.url);
                                _t.tableList.splice(index,1);
                                _t.rendering;
                            }else{
                                Swal.fire({
                                    icon: 'error',
                                    title: 'X﹏X',
                                    text: '删除数据失败！！'
                                });
                            }
                        },
                        error : function(e){
                            Swal.fire({
                                icon: 'error',
                                title: 'X﹏X',
                                text: '删除数据失败！！'
                            });
                            console.log(e.status);
                            console.log(e.responseText);
                        }
                    });
                }
              });
        },
        handleModify(index, row,){
            this.itemIndex = index + (this.currentPage-1)*this.pageSize;
                    this.itemId=row.气田编号;
                    this.editItem = {
                        气田编号: row.气田编号,
                        气田名: row.气田名,
                        气区: row.气区,
                        气藏类型: row.气藏类型,
                        埋深类型: row.埋深类型,
                        储量: row.储量,
            }
            this.dialogFormModify=true;
        },
        showMain(){
            this.showTable=false;
        },
        sbmittModify(){
            let _t = this;
            let modifyData = '';

            modifyData = {
                operation: 'modifyGasField',
                id: _t.itemId,
                newId: _t.editItem.气田编号,
                name: _t.editItem.气田名,
                area: _t.editItem.气区,
                type: _t.editItem.气藏类型,
                depthType: _t.editItem.埋深类型,
                reserves: _t.editItem.储量
            } 
            $.ajax({
                url: "GFMSService.do",
                data: modifyData,
                type: "GET",
                dataType: "json",
                success: function(data) {
                    if(data.ok == 1){
                        _t.dialogFormModify = false;
                        Swal.fire({
                            icon: 'success',
                            title: '(๑•̀ㅂ•́)و✧',
                            text: '修改数据成功'
                            });
                            Vue.set(_t.tableList, _t.itemIndex, _t.editItem);
                            _t.editItem='';
                        //console.log(data.url);
                    }
                    else{
                        _t.dialogFormVisible = false;
                        Swal.fire({
                                    icon: 'error',
                                    title: 'X﹏X',
                                    text: '修改数据失败！！'
                                });
                    }
                },
                error : function(e){
                    _t.dialogFormVisible = false;
                    Swal.fire({
                        icon: 'error',
                        title: 'X﹏X',
                        text: '修改数据失败！！'
                    });
                    console.log(e.status);
                    console.log(e.responseText);
                }
            });    
        },
        addItemFlush(){
            // this.tableList.push(this.car);
            this.gasField={
                // "气田编号": '',
                "气田名": '',
                "气区": '',
                "气藏类型": '',
                "埋深类型": '',
                "储量": ''
                }
            
        },
        addData(){
            let _t = this;
            let addData = '';
            console.log(this.gasField);
            addData = {
                operation: 'addGasField',
                name: this.gasField.气田名,
                area: this.gasField.气区,
                type: this.gasField.气藏类型,
                depthType: this.gasField.埋深类型,
                reserves: this.gasField.储量,
                // id: this.gasField.气田编号
            }
            $.ajax({
                url: "GFMSService.do",
                data: addData,
                type: "GET",
                dataType: "json",
                success: function(data) {
                    if(data.ok == 1){
                        Swal.fire({
                            icon: 'success',
                            title: '(๑•̀ㅂ•́)و✧',
                            text: '增加数据成功'
                            });
                            _t.showShelt('getAllGasFields',0);
                        //console.log(data.url);
                    }
                    else{
                        Swal.fire({
                                    icon: 'error',
                                    title: 'X﹏X',
                                    text: '增加数据失败！！'
                                });
                    }
                },
                error : function(e){
                    Swal.fire({
                        icon: 'error',
                        title: 'X﹏X',
                        text: '增加数据失败！！'
                    });
                    console.log(e.status);
                    console.log(e.responseText);
                }
            });    

        }, 
        showShelt(Dataname, tableno) {
            let _t = this;
            _t.showChart=false
            _t.loading=true;
            _t.showTable=true;
            let data = {
                operation: Dataname
            }
            if(tableno==1){
                data.operation='getAllGasfieldByArea';
                data.area = this.area;
            }
            $.ajax({
                url: "GFMSService.do",
                data: data,
                type: "GET",
                dataType: "json",
                success: function(data) {
                    if(data!=null){
                        _t.tableList = data.list;
                        _t.tableListCol = data.col;
                        if(tableno!=1)
                            _t.areaOptions = data.options;
                    }
                    else{
                        Swal.fire({
                                    icon: 'error',
                                    title: 'X﹏X',
                                    text: '获取数据失败！！'
                                });
                    }
                },
                error : function(e){
                    Swal.fire({
                        icon: 'error',
                        title: 'X﹏X',
                        text: '获取数据失败！！'
                    });
                    console.log(e.status);
                    console.log(e.responseText);
                }
            });
            setTimeout(()=>_t.loading=false, 1000);
        },
        showInfo(ops){
            this.showChart=true;
            this.refurbishChat(ops);
        },
        Initchart(){
            this.showTable=false;
            this.showChart=true;
            let _t = this;
            let data={
                operation:'getInitChart'
            }
            $.ajax({
                url: "GFMSService.do",
                data: data,
                type: "GET",
                dataType: "json",
                success: function(data) {
                    if(data.ok==1){
                        _t.typeOptions = data.typeOptions;
                        _t.depthTypeOptions = data.depthTypeOptions;
                    }
                    else{
                        Swal.fire({
                                    icon: 'error',
                                    title: 'X﹏X',
                                    text: '获取数据失败！！'
                                });
                    }
                },
                error : function(e){
                    Swal.fire({
                        icon: 'error',
                        title: 'X﹏X',
                        text: '获取数据失败！！'
                    });
                    console.log(e.status);
                    console.log(e.responseText);
                }
            });
            console.log()
        },
        refurbishChat(ops){
            let _t = this;
            let data={
                operation:'getChartData',
                type: _t.types,
                depthType: _t.depthType,
                ops: ops
            }
             $.ajax({
                url: "GFMSService.do",
                data: data,
                type: "GET",
                dataType: "json",
                success: function(data) {
                    if(data.ok==1){
                        let pieData=data.pieData;
                        _t.total=data.total;

                        let option={
                            series:[
                            {
                                type:'pie',
                                data: pieData,
                                label:{
                                    show: true,
                                    formatter:function(arg){
                                        return arg.name + ':'+'\n'+ arg.value +'亿方';
                                    },
                                    normal: {
                                    position: "inside"//此处将展示的文字在内部展示
                                    }
                                }
                            }
                            ]
                        }
                        mChart.setOption(option);
                    }
                    else{
                        Swal.fire({
                                    icon: 'error',
                                    title: 'X﹏X',
                                    text: '获取数据失败！！'
                                });
                    }
                },
                error : function(e){
                    Swal.fire({
                        icon: 'error',
                        title: 'X﹏X',
                        text: '获取数据失败！！'
                    });
                    console.log(e.status);
                    console.log(e.responseText);
                }
            });
        },
        handleCurrentChange(val) {
            this.currentPage=val;
        },
        
        }
});
new Ctor().$mount('#app');

let mChart = echarts.init(document.querySelector('#chart1'));
let pieData='';
let option={
    series:[
      {
        type:'pie',
        data: pieData,
        label:{
            show: true,
            formatter:function(arg){
                return arg.name + '：'+arg.value+' 亿方';
            }
        }
      }
    ]
}
mChart.setOption(option);
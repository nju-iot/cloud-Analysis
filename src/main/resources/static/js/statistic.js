$(document).ready(function () {
    getMeanAndData();
    function getMeanAndData() {
        $.ajax({
            type:'GET',
            url: '/device/meanAndDate',
            async:true,
            contentType:'application/json',
            processData: false,
            success: function (res) {
                var dateList=[];
                var valueList = [];
                for(var i=0;i<res.length;i++){
                    dateList.push(res[i].date);
                    valueList.push(res[i].mean);
                }
                graph1(dateList,valueList);
            },
            error: function (error) {
                alert(error)
            }
        })
    }
    function graph1(dateList, valueList) {
        console.log("dateList: " + dateList);
        console.log(valueList);
        var chartDom = document.getElementById('graph1');
        var myChart = echarts.init(chartDom);
        var option;
        option = {

            // Make gradient line here
            visualMap: [{
                show: false,
                type: 'continuous',
                seriesIndex: 0,
                min: 0,
                max: 400
            }, {
                show: false,
                type: 'continuous',
                seriesIndex: 1,
                dimension: 0,
                min: 0,
                max: dateList.length - 1
            }],


            title: [{
                left: 'center',
                text: 'Gradient along the y axis'
            }, {
                top: '55%',
                left: 'center',
                text: 'Gradient along the x axis'
            }],
            tooltip: {
                trigger: 'axis'
            },
            xAxis: [{
                data: dateList
            }, {
                data: dateList,
                gridIndex: 1
            }],
            yAxis: [{
            }, {
                gridIndex: 1
            }],
            grid: [{
                bottom: '60%'
            }, {
                top: '60%'
            }],
            series: [{
                type: 'line',
                showSymbol: false,
                data: valueList
            }, {
                type: 'line',
                showSymbol: false,
                data: valueList,
                xAxisIndex: 1,
                yAxisIndex: 1
            }]
        };
        option && myChart.setOption(option);
    }


})
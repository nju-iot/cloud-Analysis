$(document).ready(function () {
    var place_In = "In";
    var place_Out = "Out";
    getDistribution();
    getTempAndNum(place_In);
    getTempAndNum(place_Out);
    getMeanByPlace(place_In, 1);
    getMeanByPlace(place_Out, 2);
    getTempByScatter();


    function init() {
        $.ajax({
            type: 'GET',
            url: '/device/initList',
            async: false,
            contentType: 'application/json',
            processData: false,
            success: function (res) {
                alert(res);
            },
            error: function (error) {

            }
        })
    }

    /*
        生成散点图 和 回归分析图
     */
    function getTempByScatter() {
        $.ajax({
            type: 'GET',
            url: '/device/tempMeanScatter',
            async: true,
            contentType: 'application/json',
            processData: false,
            success: function (res) {
                drawScatter(res);
                draw_linear_regression(res)
            },
            error: function (error) {
                console.log(error)
            }
        })
    }

    function drawScatter(scatter_data) {
        var chartDom = document.getElementById('graph-scatter');
        var myChart = echarts.init(chartDom);
        var option;

        option = {
            title: {
                text: '室内外温度分布情况',
                left: 'center'
            },
            tooltip: {
                trigger: 'axis'
            },
            xAxis: {
                name: '室内温度',
                scale: true
            },
            yAxis: {
                name: '室外温度',
                scale: true
            },
            series: [{
                type: 'effectScatter',
                symbolSize: 20,
                data: [[32.79, 61], [27.09, 28.406]]
            }, {
                type: 'scatter',
                data: scatter_data
            }]
        };

        option && myChart.setOption(option);
    }

    function draw_linear_regression(data) {

        var chartDom = document.getElementById('linear-regression');
        var myChart = echarts.init(chartDom);

        echarts.registerTransform(ecStat.transform.regression);

        var option;
        option = {
            dataset: [{
                source: data
            }, {
                transform: {
                    type: 'ecStat:regression'
                    // 'linear' by default.
                    // config: { method: 'linear', formulaOn: 'end'}
                }
            }],
            title: {
                text: 'Linear Regression',
                subtext: 'By ecStat.regression',
                sublink: 'https://github.com/ecomfe/echarts-stat',
                left: 'center'
            },
            legend: {
                bottom: 5
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross'
                }
            },
            xAxis: {
                name: '室内温度',
                splitLine: {
                    lineStyle: {
                        type: 'dashed'
                    }
                },
            },
            yAxis: {
                name: '室外温度',
                splitLine: {
                    lineStyle: {
                        type: 'dashed'
                    }
                },
            },
            series: [{
                name: 'scatter',
                type: 'scatter'
            }, {
                name: 'line',
                type: 'line',
                datasetIndex: 1,
                symbolSize: 0.1,
                symbol: 'circle',
                label: {show: true, fontSize: 16},
                labelLayout: {dx: -20},
                encode: {label: 2, tooltip: 1}
            }]
        };
        option && myChart.setOption(option);
    }

    /*
        生成柱状图
     */
    function getTempAndNum(place) {
        $.ajax({
            type: 'POST',
            url: '/device/tempNum',
            async: true,
            data: place,
            contentType: 'application/json',
            processData: false,
            success: function (res) {
                var tempList = [];
                var numList = [];
                for (var i = 0; i < res.length; i++) {
                    tempList.push(res[i].temp);
                    numList.push(res[i].num);
                }
                drawTempNum(tempList, numList, place);
            },
            error: function (error) {
                console.log(error)
            }
        })
    }

    function drawTempNum(tempList, numList, place) {
        var id;
        var title;
        if (place == "In") {
            id = 'graph-col-in';
            title = '室内温度统计';
        } else {
            id = 'graph-col-out';
            title = '室外温度统计';
        }
        var chartDom = document.getElementById(id);
        var myChart = echarts.init(chartDom);
        var option;

        option = {
            title: {
                text: title,
                left: 'center'
            },
            tooltip: {
                trigger: 'axis'
            },
            xAxis: {
                name: '温度',
                type: 'category',
                data: tempList
            },
            yAxis: {
                name: '出现次数',
                type: 'value'
            },
            series: [{
                data: numList,
                type: 'bar',
                showBackground: true,
                backgroundStyle: {
                    color: 'rgba(180, 180, 180, 0.2)'
                }
            }]
        };
        option && myChart.setOption(option);
    }

    /*
        生成饼图
     */
    function getDistribution() {
        $.ajax({
            type: 'GET',
            url: '/device/InAndOut',
            async: true,
            contentType: 'application/json',
            processData: false,
            success: function (res) {
                drawPie(res);
            },
            error: function (error) {
                console.log(error);
            }
        })

    }

    function drawPie(data) {
        var chartDom = document.getElementById('graph-pie');
        var myChart = echarts.init(chartDom);
        var option;

        option = {
            title: {
                text: '温度数据分布情况',
                subtext: '副标题',
                left: 'center'
            },
            tooltip: {
                trigger: 'item'
            },
            legend: {
                orient: 'vertical',
                left: 'left'
            },
            series: [
                {
                    name: '温度',
                    type: 'pie',
                    radius: '50%',
                    data: data,
                    emphasis: {
                        itemStyle: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };

        option && myChart.setOption(option);

    }

    /*
        生成折线图
     */
    function getMeanByPlace(place, index) {
        $.ajax({
            type: 'POST',
            url: '/device/meanByPlace',
            async: true,
            data: place,
            contentType: 'application/json',
            processData: false,
            success: function (res) {
                var dateList = [];
                var valueList = [];
                for (var i = 0; i < res.length; i++) {
                    dateList.push(res[i].date);
                    valueList.push(res[i].mean);
                }
                if (index == 1) drawGraphforIn(dateList, valueList);
                else drawGraphforOut(dateList, valueList);
            },
            error: function (error) {
                console.log(error);
            }

        })
    }

    function drawGraphforIn(dateList, valueList) {
        var chartDom = document.getElementById('graph2');
        var myChart = echarts.init(chartDom);
        var option;
        option = {
            title: {
                left: 'center',
                text: '室内温度变化'
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            tooltip: {
                trigger: 'axis'
            },
            xAxis: {
                type: 'category',
                data: dateList
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                data: valueList,
                type: 'line'
            }]
        };
        option && myChart.setOption(option);
    }

    function drawGraphforOut(dateList, valueList) {
        var chartDom = document.getElementById('graph3');
        var myChart = echarts.init(chartDom);
        var option;
        option = {
            title: {
                left: 'center',
                text: '室外温度变化'
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            tooltip: {
                trigger: 'axis'
            },
            xAxis: {
                type: 'category',
                data: dateList
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                data: valueList,
                type: 'line',
                areaStyle: {}
            }]
        };
        option && myChart.setOption(option);
    }

})
<template>
  <div>
    <div class="card" style="padding: 15px">
      您好，{{ user?.name }}！欢迎使用本系统
    </div>

    <div style="display: flex; margin: 10px 0">
      <div style="width: 100%;" class="card">
        <div style="margin-bottom: 30px; font-size: 20px; font-weight: bold">公告列表</div>
        <div>
          <el-timeline reverse slot="reference">
            <el-timeline-item v-for="item in notices" :key="item.id" :timestamp="item.time">
              <el-popover
                  placement="right"
                  width="200"
                  trigger="hover"
                  :content="item.content">
                <span slot="reference">{{ item.title }}</span>
              </el-popover>
            </el-timeline-item>
          </el-timeline>
        </div>
      </div>
    </div>
    <div style="display: flex" v-if="user.role ==='ADMIN'">
      <div style="width: 50%; height: 400px" id="pie1" class="card"></div>
      <div style="width: 50%; height: 400px" id="pie2" class="card"></div>
    </div>
  </div>
</template>

<script>
import * as echarts from "echarts"

let pie1Options = {
  title: {
    text: '电费缴纳情况统计', // 主标题
    subtext: '统计维度: 缴纳状态', // 副标题
    left: 'center'
  },
  tooltip: {
    trigger: 'item',
    formatter: '{a} <br/>{b} : {c} ({d}%)'
  },
  legend: {
    orient: 'vertical',
    left: 'left'
  },
  series: [
    {
      name: '', // 鼠标移上去显示内容
      type: 'pie',
      radius: '50%',
      center: ['50%', '60%'],
      data: [
        {value: 100, name: '已缴费'},
        {value: 100, name: '未缴费'},
      ]
    }
  ]
}
let pie2Options = {
  title: {
    text: '宿舍住宿情况统计', // 主标题
    subtext: '统计维度: 是否住满', // 副标题
    left: 'center'
  },
  tooltip: {
    trigger: 'item',
    formatter: '{a} <br/>{b} : {c} ({d}%)'
  },
  legend: {
    orient: 'vertical',
    left: 'left'
  },
  series: [
    {
      name: '', // 鼠标移上去显示内容
      type: 'pie',
      radius: '50%',
      center: ['50%', '60%'],
      data: [
        {value: 100, name: '已住满'},
        {value: 100, name: '未住满'},
      ]
    }
  ]
}
export default {
  name: 'Home',
  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      notices: []
    }
  },
  created() {
    this.$request.get('/notice/selectAll').then(res => {
      this.notices = res.data || []
    })
    if (this.user.role === 'ADMIN') {
      this.loadElectro()
      this.loadStay()
    }
  },
  methods: {
    loadElectro() {
      this.$request.get('/echarts/electro').then(res => {
        if (res.code === '200') {
          let chartDom = document.getElementById('pie1');
          let myChart = echarts.init(chartDom);
          pie1Options.series[0].data = res.data
          myChart.setOption(pie1Options)
        }
      })
    },
    loadStay() {
      this.$request.get('/echarts/stay').then(res => {
        if (res.code === '200') {
          let chartDom = document.getElementById('pie2');
          let myChart = echarts.init(chartDom);
          pie2Options.series[0].data = res.data
          myChart.setOption(pie2Options)
        }
      })
    }
  }
}
</script>

<template>
  <div>
    <div class="search">
      <el-input placeholder="请输入宿舍号查询" style="width: 200px" v-model="dormitoryName"></el-input>
      <el-button type="info" plain style="margin-left: 10px" @click="load(1)">查询</el-button>
      <el-button type="warning" plain style="margin-left: 10px" @click="reset">重置</el-button>
    </div>

    <div class="operation" v-if="user.role === 'STUDENT'">
      <el-button type="primary" plain @click="handleAdd">我要报修</el-button>
    </div>

    <div class="table">
      <el-table :data="tableData" stripe>
        <el-table-column prop="id" label="序号" width="80" align="center" sortable></el-table-column>
        <el-table-column prop="studentName" label="报修人" show-overflow-tooltip></el-table-column>
        <el-table-column prop="dormitoryName" label="报修宿舍" show-overflow-tooltip></el-table-column>
        <el-table-column prop="content" label="内容" show-overflow-tooltip></el-table-column>
        <el-table-column prop="img" label="图片" show-overflow-tooltip>
          <template v-slot="scope">
            <div style="display: flex; align-items: center">
              <el-image style="width: 40px; height: 40px" v-if="scope.row.img"
                        :src="scope.row.img" :preview-src-list="[scope.row.img]"></el-image>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="time" label="报修时间"></el-table-column>
        <el-table-column prop="status" label="处理状态"></el-table-column>

        <el-table-column label="操作" width="180" align="center">
          <template v-slot="scope" v-if="user.role === 'STUDENT'">
            <el-button plain type="primary" @click="handleEdit(scope.row)" size="mini" :disabled="scope.row.status === '已处理'">编辑</el-button>
            <el-button plain type="danger" size="mini" @click=del(scope.row.id) :disabled="scope.row.status === '已处理'">删除</el-button>
          </template>
          <template v-slot="scope" v-else>
            <el-button plain type="primary" @click="handle(scope.row)" size="mini" :disabled="scope.row.status === '已处理'">已处理</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="pagination">
        <el-pagination
            background
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[5, 10, 20]"
            :page-size="pageSize"
            layout="total, prev, pager, next"
            :total="total">
        </el-pagination>
      </div>
    </div>


    <el-dialog title="报修信息" :visible.sync="fromVisible" width="40%" :close-on-click-modal="false" destroy-on-close>
      <el-form label-width="100px" style="padding-right: 50px" :model="form" :rules="rules" ref="formRef">
        <el-form-item prop="dormitoryId" label="报修宿舍">
          <el-select v-model="form.dormitoryId" placeholder="请选择" style="width: 100%">
            <el-option v-for="item in dormitoryData" :key="item.id" :label="item.code" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="content" label="报修说明">
          <el-input type="textarea" :rows="5" v-model="form.content" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="报修图片">
          <el-upload
              class="avatar-uploader"
              :action="$baseUrl + '/files/upload'"
              :headers="{ token: user.token }"
              list-type="picture"
              :on-success="handleImgSuccess"
          >
            <el-button type="primary">上传图片</el-button>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="fromVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
export default {
  name: "Fix",
  data() {
    return {
      tableData: [],  // 所有的数据
      pageNum: 1,   // 当前的页码
      pageSize: 10,  // 每页显示的个数
      total: 0,
      dormitoryName: null,
      fromVisible: false,
      form: {},
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      rules: {
        dormitoryId: [
          {required: true, message: '请选择宿舍', trigger: 'blur'},
        ],
        content: [
          {required: true, message: '请输入内容', trigger: 'blur'},
        ]
      },
      ids: [],
      dormitoryData: []
    }
  },
  created() {
    this.load(1)
    this.loadDormitory()
  },
  methods: {
    loadDormitory() {
      this.$request.get('/dormitory/selectAll').then(res => {
        if (res.code === '200') {
          this.dormitoryData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    handleAdd() {   // 新增数据
      this.form = {
        studentId: this.user.id,
        status: '待处理'
      }  // 新增数据的时候清空数据
      this.fromVisible = true   // 打开弹窗
    },
    handleEdit(row) {   // 编辑数据
      this.form = JSON.parse(JSON.stringify(row))  // 给form对象赋值  注意要深拷贝数据
      this.fromVisible = true   // 打开弹窗
    },
    save() {   // 保存按钮触发的逻辑  它会触发新增或者更新
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.submit()
        }
      })
    },
    submit() {
      this.$request({
        url: this.form.id ? '/fix/update' : '/fix/add',
        method: this.form.id ? 'PUT' : 'POST',
        data: this.form
      }).then(res => {
        if (res.code === '200') {  // 表示成功保存
          this.$message.success('保存成功')
          this.load(1)
          this.fromVisible = false
        } else {
          this.$message.error(res.msg)  // 弹出错误的信息
        }
      })
    },
    del(id) {   // 单个删除
      this.$confirm('您确定删除吗？', '确认删除', {type: "warning"}).then(response => {
        this.$request.delete('/fix/delete/' + id).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('操作成功')
            this.load(1)
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },
    load(pageNum) {  // 分页查询
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/fix/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          dormitoryName: this.dormitoryName,
        }
      }).then(res => {
        this.tableData = res.data?.list
        this.total = res.data?.total
      })
    },
    reset() {
      this.dormitoryName = null
      this.load(1)
    },
    handleCurrentChange(pageNum) {
      this.load(pageNum)
    },
    handleImgSuccess(res) {
      this.form.img = res.data
    },
    handle(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.form.status = '已处理'
      this.submit()
    }
  }
}
</script>

<style scoped>

</style>
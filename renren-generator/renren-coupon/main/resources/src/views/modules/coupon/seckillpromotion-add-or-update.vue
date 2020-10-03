<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="活动标题" prop="title">
      <el-input v-model="dataForm.title" placeholder="活动标题"></el-input>
    </el-form-item>
    <el-form-item label="开始日期" prop="startTime">
      <el-input v-model="dataForm.startTime" placeholder="开始日期"></el-input>
    </el-form-item>
    <el-form-item label="结束日期" prop="endTime">
      <el-input v-model="dataForm.endTime" placeholder="结束日期"></el-input>
    </el-form-item>
    <el-form-item label="上下线状态" prop="status">
      <el-input v-model="dataForm.status" placeholder="上下线状态"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="创建人" prop="userId">
      <el-input v-model="dataForm.userId" placeholder="创建人"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          title: '',
          startTime: '',
          endTime: '',
          status: '',
          createTime: '',
          userId: ''
        },
        dataRule: {
          title: [
            { required: true, message: '活动标题不能为空', trigger: 'blur' }
          ],
          startTime: [
            { required: true, message: '开始日期不能为空', trigger: 'blur' }
          ],
          endTime: [
            { required: true, message: '结束日期不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '上下线状态不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          userId: [
            { required: true, message: '创建人不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/coupon/seckillpromotion/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.title = data.seckillPromotion.title
                this.dataForm.startTime = data.seckillPromotion.startTime
                this.dataForm.endTime = data.seckillPromotion.endTime
                this.dataForm.status = data.seckillPromotion.status
                this.dataForm.createTime = data.seckillPromotion.createTime
                this.dataForm.userId = data.seckillPromotion.userId
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/coupon/seckillpromotion/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'title': this.dataForm.title,
                'startTime': this.dataForm.startTime,
                'endTime': this.dataForm.endTime,
                'status': this.dataForm.status,
                'createTime': this.dataForm.createTime,
                'userId': this.dataForm.userId
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>

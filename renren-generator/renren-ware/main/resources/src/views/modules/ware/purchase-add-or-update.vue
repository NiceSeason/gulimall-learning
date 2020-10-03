<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="采购人id" prop="assigneeId">
      <el-input v-model="dataForm.assigneeId" placeholder="采购人id"></el-input>
    </el-form-item>
    <el-form-item label="采购人名" prop="assigneeName">
      <el-input v-model="dataForm.assigneeName" placeholder="采购人名"></el-input>
    </el-form-item>
    <el-form-item label="联系方式" prop="phone">
      <el-input v-model="dataForm.phone" placeholder="联系方式"></el-input>
    </el-form-item>
    <el-form-item label="优先级" prop="priority">
      <el-input v-model="dataForm.priority" placeholder="优先级"></el-input>
    </el-form-item>
    <el-form-item label="状态" prop="status">
      <el-input v-model="dataForm.status" placeholder="状态"></el-input>
    </el-form-item>
    <el-form-item label="仓库id" prop="wareId">
      <el-input v-model="dataForm.wareId" placeholder="仓库id"></el-input>
    </el-form-item>
    <el-form-item label="总金额" prop="amount">
      <el-input v-model="dataForm.amount" placeholder="总金额"></el-input>
    </el-form-item>
    <el-form-item label="创建日期" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建日期"></el-input>
    </el-form-item>
    <el-form-item label="更新日期" prop="updateTime">
      <el-input v-model="dataForm.updateTime" placeholder="更新日期"></el-input>
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
          assigneeId: '',
          assigneeName: '',
          phone: '',
          priority: '',
          status: '',
          wareId: '',
          amount: '',
          createTime: '',
          updateTime: ''
        },
        dataRule: {
          assigneeId: [
            { required: true, message: '采购人id不能为空', trigger: 'blur' }
          ],
          assigneeName: [
            { required: true, message: '采购人名不能为空', trigger: 'blur' }
          ],
          phone: [
            { required: true, message: '联系方式不能为空', trigger: 'blur' }
          ],
          priority: [
            { required: true, message: '优先级不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          wareId: [
            { required: true, message: '仓库id不能为空', trigger: 'blur' }
          ],
          amount: [
            { required: true, message: '总金额不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建日期不能为空', trigger: 'blur' }
          ],
          updateTime: [
            { required: true, message: '更新日期不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/ware/purchase/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.assigneeId = data.purchase.assigneeId
                this.dataForm.assigneeName = data.purchase.assigneeName
                this.dataForm.phone = data.purchase.phone
                this.dataForm.priority = data.purchase.priority
                this.dataForm.status = data.purchase.status
                this.dataForm.wareId = data.purchase.wareId
                this.dataForm.amount = data.purchase.amount
                this.dataForm.createTime = data.purchase.createTime
                this.dataForm.updateTime = data.purchase.updateTime
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
              url: this.$http.adornUrl(`/ware/purchase/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'assigneeId': this.dataForm.assigneeId,
                'assigneeName': this.dataForm.assigneeName,
                'phone': this.dataForm.phone,
                'priority': this.dataForm.priority,
                'status': this.dataForm.status,
                'wareId': this.dataForm.wareId,
                'amount': this.dataForm.amount,
                'createTime': this.dataForm.createTime,
                'updateTime': this.dataForm.updateTime
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

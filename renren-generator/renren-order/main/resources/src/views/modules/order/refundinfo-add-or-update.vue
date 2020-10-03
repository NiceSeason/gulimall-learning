<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="退款的订单" prop="orderReturnId">
      <el-input v-model="dataForm.orderReturnId" placeholder="退款的订单"></el-input>
    </el-form-item>
    <el-form-item label="退款金额" prop="refund">
      <el-input v-model="dataForm.refund" placeholder="退款金额"></el-input>
    </el-form-item>
    <el-form-item label="退款交易流水号" prop="refundSn">
      <el-input v-model="dataForm.refundSn" placeholder="退款交易流水号"></el-input>
    </el-form-item>
    <el-form-item label="退款状态" prop="refundStatus">
      <el-input v-model="dataForm.refundStatus" placeholder="退款状态"></el-input>
    </el-form-item>
    <el-form-item label="退款渠道[1-支付宝，2-微信，3-银联，4-汇款]" prop="refundChannel">
      <el-input v-model="dataForm.refundChannel" placeholder="退款渠道[1-支付宝，2-微信，3-银联，4-汇款]"></el-input>
    </el-form-item>
    <el-form-item label="" prop="refundContent">
      <el-input v-model="dataForm.refundContent" placeholder=""></el-input>
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
          orderReturnId: '',
          refund: '',
          refundSn: '',
          refundStatus: '',
          refundChannel: '',
          refundContent: ''
        },
        dataRule: {
          orderReturnId: [
            { required: true, message: '退款的订单不能为空', trigger: 'blur' }
          ],
          refund: [
            { required: true, message: '退款金额不能为空', trigger: 'blur' }
          ],
          refundSn: [
            { required: true, message: '退款交易流水号不能为空', trigger: 'blur' }
          ],
          refundStatus: [
            { required: true, message: '退款状态不能为空', trigger: 'blur' }
          ],
          refundChannel: [
            { required: true, message: '退款渠道[1-支付宝，2-微信，3-银联，4-汇款]不能为空', trigger: 'blur' }
          ],
          refundContent: [
            { required: true, message: '不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/order/refundinfo/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.orderReturnId = data.refundInfo.orderReturnId
                this.dataForm.refund = data.refundInfo.refund
                this.dataForm.refundSn = data.refundInfo.refundSn
                this.dataForm.refundStatus = data.refundInfo.refundStatus
                this.dataForm.refundChannel = data.refundInfo.refundChannel
                this.dataForm.refundContent = data.refundInfo.refundContent
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
              url: this.$http.adornUrl(`/order/refundinfo/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'orderReturnId': this.dataForm.orderReturnId,
                'refund': this.dataForm.refund,
                'refundSn': this.dataForm.refundSn,
                'refundStatus': this.dataForm.refundStatus,
                'refundChannel': this.dataForm.refundChannel,
                'refundContent': this.dataForm.refundContent
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

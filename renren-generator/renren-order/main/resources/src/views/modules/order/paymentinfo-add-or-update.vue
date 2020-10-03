<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="订单号（对外业务号）" prop="orderSn">
      <el-input v-model="dataForm.orderSn" placeholder="订单号（对外业务号）"></el-input>
    </el-form-item>
    <el-form-item label="订单id" prop="orderId">
      <el-input v-model="dataForm.orderId" placeholder="订单id"></el-input>
    </el-form-item>
    <el-form-item label="支付宝交易流水号" prop="alipayTradeNo">
      <el-input v-model="dataForm.alipayTradeNo" placeholder="支付宝交易流水号"></el-input>
    </el-form-item>
    <el-form-item label="支付总金额" prop="totalAmount">
      <el-input v-model="dataForm.totalAmount" placeholder="支付总金额"></el-input>
    </el-form-item>
    <el-form-item label="交易内容" prop="subject">
      <el-input v-model="dataForm.subject" placeholder="交易内容"></el-input>
    </el-form-item>
    <el-form-item label="支付状态" prop="paymentStatus">
      <el-input v-model="dataForm.paymentStatus" placeholder="支付状态"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="确认时间" prop="confirmTime">
      <el-input v-model="dataForm.confirmTime" placeholder="确认时间"></el-input>
    </el-form-item>
    <el-form-item label="回调内容" prop="callbackContent">
      <el-input v-model="dataForm.callbackContent" placeholder="回调内容"></el-input>
    </el-form-item>
    <el-form-item label="回调时间" prop="callbackTime">
      <el-input v-model="dataForm.callbackTime" placeholder="回调时间"></el-input>
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
          orderSn: '',
          orderId: '',
          alipayTradeNo: '',
          totalAmount: '',
          subject: '',
          paymentStatus: '',
          createTime: '',
          confirmTime: '',
          callbackContent: '',
          callbackTime: ''
        },
        dataRule: {
          orderSn: [
            { required: true, message: '订单号（对外业务号）不能为空', trigger: 'blur' }
          ],
          orderId: [
            { required: true, message: '订单id不能为空', trigger: 'blur' }
          ],
          alipayTradeNo: [
            { required: true, message: '支付宝交易流水号不能为空', trigger: 'blur' }
          ],
          totalAmount: [
            { required: true, message: '支付总金额不能为空', trigger: 'blur' }
          ],
          subject: [
            { required: true, message: '交易内容不能为空', trigger: 'blur' }
          ],
          paymentStatus: [
            { required: true, message: '支付状态不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          confirmTime: [
            { required: true, message: '确认时间不能为空', trigger: 'blur' }
          ],
          callbackContent: [
            { required: true, message: '回调内容不能为空', trigger: 'blur' }
          ],
          callbackTime: [
            { required: true, message: '回调时间不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/order/paymentinfo/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.orderSn = data.paymentInfo.orderSn
                this.dataForm.orderId = data.paymentInfo.orderId
                this.dataForm.alipayTradeNo = data.paymentInfo.alipayTradeNo
                this.dataForm.totalAmount = data.paymentInfo.totalAmount
                this.dataForm.subject = data.paymentInfo.subject
                this.dataForm.paymentStatus = data.paymentInfo.paymentStatus
                this.dataForm.createTime = data.paymentInfo.createTime
                this.dataForm.confirmTime = data.paymentInfo.confirmTime
                this.dataForm.callbackContent = data.paymentInfo.callbackContent
                this.dataForm.callbackTime = data.paymentInfo.callbackTime
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
              url: this.$http.adornUrl(`/order/paymentinfo/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'orderSn': this.dataForm.orderSn,
                'orderId': this.dataForm.orderId,
                'alipayTradeNo': this.dataForm.alipayTradeNo,
                'totalAmount': this.dataForm.totalAmount,
                'subject': this.dataForm.subject,
                'paymentStatus': this.dataForm.paymentStatus,
                'createTime': this.dataForm.createTime,
                'confirmTime': this.dataForm.confirmTime,
                'callbackContent': this.dataForm.callbackContent,
                'callbackTime': this.dataForm.callbackTime
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

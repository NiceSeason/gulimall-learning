<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="采购单id" prop="purchaseId">
      <el-input v-model="dataForm.purchaseId" placeholder="采购单id"></el-input>
    </el-form-item>
    <el-form-item label="采购商品id" prop="skuId">
      <el-input v-model="dataForm.skuId" placeholder="采购商品id"></el-input>
    </el-form-item>
    <el-form-item label="采购数量" prop="skuNum">
      <el-input v-model="dataForm.skuNum" placeholder="采购数量"></el-input>
    </el-form-item>
    <el-form-item label="采购金额" prop="skuPrice">
      <el-input v-model="dataForm.skuPrice" placeholder="采购金额"></el-input>
    </el-form-item>
    <el-form-item label="仓库id" prop="wareId">
      <el-input v-model="dataForm.wareId" placeholder="仓库id"></el-input>
    </el-form-item>
    <el-form-item label="状态[0新建，1已分配，2正在采购，3已完成，4采购失败]" prop="status">
      <el-input v-model="dataForm.status" placeholder="状态[0新建，1已分配，2正在采购，3已完成，4采购失败]"></el-input>
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
          purchaseId: '',
          skuId: '',
          skuNum: '',
          skuPrice: '',
          wareId: '',
          status: ''
        },
        dataRule: {
          purchaseId: [
            { required: true, message: '采购单id不能为空', trigger: 'blur' }
          ],
          skuId: [
            { required: true, message: '采购商品id不能为空', trigger: 'blur' }
          ],
          skuNum: [
            { required: true, message: '采购数量不能为空', trigger: 'blur' }
          ],
          skuPrice: [
            { required: true, message: '采购金额不能为空', trigger: 'blur' }
          ],
          wareId: [
            { required: true, message: '仓库id不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '状态[0新建，1已分配，2正在采购，3已完成，4采购失败]不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/ware/purchasedetail/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.purchaseId = data.purchaseDetail.purchaseId
                this.dataForm.skuId = data.purchaseDetail.skuId
                this.dataForm.skuNum = data.purchaseDetail.skuNum
                this.dataForm.skuPrice = data.purchaseDetail.skuPrice
                this.dataForm.wareId = data.purchaseDetail.wareId
                this.dataForm.status = data.purchaseDetail.status
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
              url: this.$http.adornUrl(`/ware/purchasedetail/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'purchaseId': this.dataForm.purchaseId,
                'skuId': this.dataForm.skuId,
                'skuNum': this.dataForm.skuNum,
                'skuPrice': this.dataForm.skuPrice,
                'wareId': this.dataForm.wareId,
                'status': this.dataForm.status
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

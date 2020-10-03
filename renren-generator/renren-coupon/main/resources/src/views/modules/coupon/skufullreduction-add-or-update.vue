<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="spu_id" prop="skuId">
      <el-input v-model="dataForm.skuId" placeholder="spu_id"></el-input>
    </el-form-item>
    <el-form-item label="满多少" prop="fullPrice">
      <el-input v-model="dataForm.fullPrice" placeholder="满多少"></el-input>
    </el-form-item>
    <el-form-item label="减多少" prop="reducePrice">
      <el-input v-model="dataForm.reducePrice" placeholder="减多少"></el-input>
    </el-form-item>
    <el-form-item label="是否参与其他优惠" prop="addOther">
      <el-input v-model="dataForm.addOther" placeholder="是否参与其他优惠"></el-input>
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
          skuId: '',
          fullPrice: '',
          reducePrice: '',
          addOther: ''
        },
        dataRule: {
          skuId: [
            { required: true, message: 'spu_id不能为空', trigger: 'blur' }
          ],
          fullPrice: [
            { required: true, message: '满多少不能为空', trigger: 'blur' }
          ],
          reducePrice: [
            { required: true, message: '减多少不能为空', trigger: 'blur' }
          ],
          addOther: [
            { required: true, message: '是否参与其他优惠不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/coupon/skufullreduction/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.skuId = data.skuFullReduction.skuId
                this.dataForm.fullPrice = data.skuFullReduction.fullPrice
                this.dataForm.reducePrice = data.skuFullReduction.reducePrice
                this.dataForm.addOther = data.skuFullReduction.addOther
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
              url: this.$http.adornUrl(`/coupon/skufullreduction/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'skuId': this.dataForm.skuId,
                'fullPrice': this.dataForm.fullPrice,
                'reducePrice': this.dataForm.reducePrice,
                'addOther': this.dataForm.addOther
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

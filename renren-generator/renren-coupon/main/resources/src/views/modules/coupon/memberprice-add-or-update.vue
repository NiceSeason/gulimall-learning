<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="sku_id" prop="skuId">
      <el-input v-model="dataForm.skuId" placeholder="sku_id"></el-input>
    </el-form-item>
    <el-form-item label="会员等级id" prop="memberLevelId">
      <el-input v-model="dataForm.memberLevelId" placeholder="会员等级id"></el-input>
    </el-form-item>
    <el-form-item label="会员等级名" prop="memberLevelName">
      <el-input v-model="dataForm.memberLevelName" placeholder="会员等级名"></el-input>
    </el-form-item>
    <el-form-item label="会员对应价格" prop="memberPrice">
      <el-input v-model="dataForm.memberPrice" placeholder="会员对应价格"></el-input>
    </el-form-item>
    <el-form-item label="可否叠加其他优惠[0-不可叠加优惠，1-可叠加]" prop="addOther">
      <el-input v-model="dataForm.addOther" placeholder="可否叠加其他优惠[0-不可叠加优惠，1-可叠加]"></el-input>
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
          memberLevelId: '',
          memberLevelName: '',
          memberPrice: '',
          addOther: ''
        },
        dataRule: {
          skuId: [
            { required: true, message: 'sku_id不能为空', trigger: 'blur' }
          ],
          memberLevelId: [
            { required: true, message: '会员等级id不能为空', trigger: 'blur' }
          ],
          memberLevelName: [
            { required: true, message: '会员等级名不能为空', trigger: 'blur' }
          ],
          memberPrice: [
            { required: true, message: '会员对应价格不能为空', trigger: 'blur' }
          ],
          addOther: [
            { required: true, message: '可否叠加其他优惠[0-不可叠加优惠，1-可叠加]不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/coupon/memberprice/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.skuId = data.memberPrice.skuId
                this.dataForm.memberLevelId = data.memberPrice.memberLevelId
                this.dataForm.memberLevelName = data.memberPrice.memberLevelName
                this.dataForm.memberPrice = data.memberPrice.memberPrice
                this.dataForm.addOther = data.memberPrice.addOther
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
              url: this.$http.adornUrl(`/coupon/memberprice/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'skuId': this.dataForm.skuId,
                'memberLevelId': this.dataForm.memberLevelId,
                'memberLevelName': this.dataForm.memberLevelName,
                'memberPrice': this.dataForm.memberPrice,
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

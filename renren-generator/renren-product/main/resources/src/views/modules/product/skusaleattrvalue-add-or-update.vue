<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="sku_id" prop="skuId">
      <el-input v-model="dataForm.skuId" placeholder="sku_id"></el-input>
    </el-form-item>
    <el-form-item label="attr_id" prop="attrId">
      <el-input v-model="dataForm.attrId" placeholder="attr_id"></el-input>
    </el-form-item>
    <el-form-item label="销售属性名" prop="attrName">
      <el-input v-model="dataForm.attrName" placeholder="销售属性名"></el-input>
    </el-form-item>
    <el-form-item label="销售属性值" prop="attrValue">
      <el-input v-model="dataForm.attrValue" placeholder="销售属性值"></el-input>
    </el-form-item>
    <el-form-item label="顺序" prop="attrSort">
      <el-input v-model="dataForm.attrSort" placeholder="顺序"></el-input>
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
          attrId: '',
          attrName: '',
          attrValue: '',
          attrSort: ''
        },
        dataRule: {
          skuId: [
            { required: true, message: 'sku_id不能为空', trigger: 'blur' }
          ],
          attrId: [
            { required: true, message: 'attr_id不能为空', trigger: 'blur' }
          ],
          attrName: [
            { required: true, message: '销售属性名不能为空', trigger: 'blur' }
          ],
          attrValue: [
            { required: true, message: '销售属性值不能为空', trigger: 'blur' }
          ],
          attrSort: [
            { required: true, message: '顺序不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/product/skusaleattrvalue/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.skuId = data.skuSaleAttrValue.skuId
                this.dataForm.attrId = data.skuSaleAttrValue.attrId
                this.dataForm.attrName = data.skuSaleAttrValue.attrName
                this.dataForm.attrValue = data.skuSaleAttrValue.attrValue
                this.dataForm.attrSort = data.skuSaleAttrValue.attrSort
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
              url: this.$http.adornUrl(`/product/skusaleattrvalue/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'skuId': this.dataForm.skuId,
                'attrId': this.dataForm.attrId,
                'attrName': this.dataForm.attrName,
                'attrValue': this.dataForm.attrValue,
                'attrSort': this.dataForm.attrSort
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

<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="商品id" prop="spuId">
      <el-input v-model="dataForm.spuId" placeholder="商品id"></el-input>
    </el-form-item>
    <el-form-item label="属性id" prop="attrId">
      <el-input v-model="dataForm.attrId" placeholder="属性id"></el-input>
    </el-form-item>
    <el-form-item label="属性名" prop="attrName">
      <el-input v-model="dataForm.attrName" placeholder="属性名"></el-input>
    </el-form-item>
    <el-form-item label="属性值" prop="attrValue">
      <el-input v-model="dataForm.attrValue" placeholder="属性值"></el-input>
    </el-form-item>
    <el-form-item label="顺序" prop="attrSort">
      <el-input v-model="dataForm.attrSort" placeholder="顺序"></el-input>
    </el-form-item>
    <el-form-item label="快速展示【是否展示在介绍上；0-否 1-是】" prop="quickShow">
      <el-input v-model="dataForm.quickShow" placeholder="快速展示【是否展示在介绍上；0-否 1-是】"></el-input>
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
          spuId: '',
          attrId: '',
          attrName: '',
          attrValue: '',
          attrSort: '',
          quickShow: ''
        },
        dataRule: {
          spuId: [
            { required: true, message: '商品id不能为空', trigger: 'blur' }
          ],
          attrId: [
            { required: true, message: '属性id不能为空', trigger: 'blur' }
          ],
          attrName: [
            { required: true, message: '属性名不能为空', trigger: 'blur' }
          ],
          attrValue: [
            { required: true, message: '属性值不能为空', trigger: 'blur' }
          ],
          attrSort: [
            { required: true, message: '顺序不能为空', trigger: 'blur' }
          ],
          quickShow: [
            { required: true, message: '快速展示【是否展示在介绍上；0-否 1-是】不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/product/productattrvalue/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.spuId = data.productAttrValue.spuId
                this.dataForm.attrId = data.productAttrValue.attrId
                this.dataForm.attrName = data.productAttrValue.attrName
                this.dataForm.attrValue = data.productAttrValue.attrValue
                this.dataForm.attrSort = data.productAttrValue.attrSort
                this.dataForm.quickShow = data.productAttrValue.quickShow
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
              url: this.$http.adornUrl(`/product/productattrvalue/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'spuId': this.dataForm.spuId,
                'attrId': this.dataForm.attrId,
                'attrName': this.dataForm.attrName,
                'attrValue': this.dataForm.attrValue,
                'attrSort': this.dataForm.attrSort,
                'quickShow': this.dataForm.quickShow
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

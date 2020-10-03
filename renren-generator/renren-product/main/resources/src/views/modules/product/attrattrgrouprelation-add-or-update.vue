<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="属性id" prop="attrId">
      <el-input v-model="dataForm.attrId" placeholder="属性id"></el-input>
    </el-form-item>
    <el-form-item label="属性分组id" prop="attrGroupId">
      <el-input v-model="dataForm.attrGroupId" placeholder="属性分组id"></el-input>
    </el-form-item>
    <el-form-item label="属性组内排序" prop="attrSort">
      <el-input v-model="dataForm.attrSort" placeholder="属性组内排序"></el-input>
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
          attrId: '',
          attrGroupId: '',
          attrSort: ''
        },
        dataRule: {
          attrId: [
            { required: true, message: '属性id不能为空', trigger: 'blur' }
          ],
          attrGroupId: [
            { required: true, message: '属性分组id不能为空', trigger: 'blur' }
          ],
          attrSort: [
            { required: true, message: '属性组内排序不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/product/attrattrgrouprelation/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.attrId = data.attrAttrgroupRelation.attrId
                this.dataForm.attrGroupId = data.attrAttrgroupRelation.attrGroupId
                this.dataForm.attrSort = data.attrAttrgroupRelation.attrSort
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
              url: this.$http.adornUrl(`/product/attrattrgrouprelation/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'attrId': this.dataForm.attrId,
                'attrGroupId': this.dataForm.attrGroupId,
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

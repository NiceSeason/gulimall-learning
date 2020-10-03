<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="subject_id" prop="subjectId">
      <el-input v-model="dataForm.subjectId" placeholder="subject_id"></el-input>
    </el-form-item>
    <el-form-item label="subject_name" prop="subjectName">
      <el-input v-model="dataForm.subjectName" placeholder="subject_name"></el-input>
    </el-form-item>
    <el-form-item label="subject_img" prop="subjectImg">
      <el-input v-model="dataForm.subjectImg" placeholder="subject_img"></el-input>
    </el-form-item>
    <el-form-item label="活动url" prop="subjectUrll">
      <el-input v-model="dataForm.subjectUrll" placeholder="活动url"></el-input>
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
          subjectId: '',
          subjectName: '',
          subjectImg: '',
          subjectUrll: ''
        },
        dataRule: {
          subjectId: [
            { required: true, message: 'subject_id不能为空', trigger: 'blur' }
          ],
          subjectName: [
            { required: true, message: 'subject_name不能为空', trigger: 'blur' }
          ],
          subjectImg: [
            { required: true, message: 'subject_img不能为空', trigger: 'blur' }
          ],
          subjectUrll: [
            { required: true, message: '活动url不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/member/membercollectsubject/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.subjectId = data.memberCollectSubject.subjectId
                this.dataForm.subjectName = data.memberCollectSubject.subjectName
                this.dataForm.subjectImg = data.memberCollectSubject.subjectImg
                this.dataForm.subjectUrll = data.memberCollectSubject.subjectUrll
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
              url: this.$http.adornUrl(`/member/membercollectsubject/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'subjectId': this.dataForm.subjectId,
                'subjectName': this.dataForm.subjectName,
                'subjectImg': this.dataForm.subjectImg,
                'subjectUrll': this.dataForm.subjectUrll
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

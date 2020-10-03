<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="专题名字" prop="name">
      <el-input v-model="dataForm.name" placeholder="专题名字"></el-input>
    </el-form-item>
    <el-form-item label="专题标题" prop="title">
      <el-input v-model="dataForm.title" placeholder="专题标题"></el-input>
    </el-form-item>
    <el-form-item label="专题副标题" prop="subTitle">
      <el-input v-model="dataForm.subTitle" placeholder="专题副标题"></el-input>
    </el-form-item>
    <el-form-item label="显示状态" prop="status">
      <el-input v-model="dataForm.status" placeholder="显示状态"></el-input>
    </el-form-item>
    <el-form-item label="详情连接" prop="url">
      <el-input v-model="dataForm.url" placeholder="详情连接"></el-input>
    </el-form-item>
    <el-form-item label="排序" prop="sort">
      <el-input v-model="dataForm.sort" placeholder="排序"></el-input>
    </el-form-item>
    <el-form-item label="专题图片地址" prop="img">
      <el-input v-model="dataForm.img" placeholder="专题图片地址"></el-input>
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
          name: '',
          title: '',
          subTitle: '',
          status: '',
          url: '',
          sort: '',
          img: ''
        },
        dataRule: {
          name: [
            { required: true, message: '专题名字不能为空', trigger: 'blur' }
          ],
          title: [
            { required: true, message: '专题标题不能为空', trigger: 'blur' }
          ],
          subTitle: [
            { required: true, message: '专题副标题不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '显示状态不能为空', trigger: 'blur' }
          ],
          url: [
            { required: true, message: '详情连接不能为空', trigger: 'blur' }
          ],
          sort: [
            { required: true, message: '排序不能为空', trigger: 'blur' }
          ],
          img: [
            { required: true, message: '专题图片地址不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/coupon/homesubject/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.homeSubject.name
                this.dataForm.title = data.homeSubject.title
                this.dataForm.subTitle = data.homeSubject.subTitle
                this.dataForm.status = data.homeSubject.status
                this.dataForm.url = data.homeSubject.url
                this.dataForm.sort = data.homeSubject.sort
                this.dataForm.img = data.homeSubject.img
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
              url: this.$http.adornUrl(`/coupon/homesubject/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'name': this.dataForm.name,
                'title': this.dataForm.title,
                'subTitle': this.dataForm.subTitle,
                'status': this.dataForm.status,
                'url': this.dataForm.url,
                'sort': this.dataForm.sort,
                'img': this.dataForm.img
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

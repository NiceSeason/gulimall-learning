<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="sku_id" prop="skuId">
      <el-input v-model="dataForm.skuId" placeholder="sku_id"></el-input>
    </el-form-item>
    <el-form-item label="spu_id" prop="spuId">
      <el-input v-model="dataForm.spuId" placeholder="spu_id"></el-input>
    </el-form-item>
    <el-form-item label="商品名字" prop="spuName">
      <el-input v-model="dataForm.spuName" placeholder="商品名字"></el-input>
    </el-form-item>
    <el-form-item label="会员昵称" prop="memberNickName">
      <el-input v-model="dataForm.memberNickName" placeholder="会员昵称"></el-input>
    </el-form-item>
    <el-form-item label="星级" prop="star">
      <el-input v-model="dataForm.star" placeholder="星级"></el-input>
    </el-form-item>
    <el-form-item label="会员ip" prop="memberIp">
      <el-input v-model="dataForm.memberIp" placeholder="会员ip"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="显示状态[0-不显示，1-显示]" prop="showStatus">
      <el-input v-model="dataForm.showStatus" placeholder="显示状态[0-不显示，1-显示]"></el-input>
    </el-form-item>
    <el-form-item label="购买时属性组合" prop="spuAttributes">
      <el-input v-model="dataForm.spuAttributes" placeholder="购买时属性组合"></el-input>
    </el-form-item>
    <el-form-item label="点赞数" prop="likesCount">
      <el-input v-model="dataForm.likesCount" placeholder="点赞数"></el-input>
    </el-form-item>
    <el-form-item label="回复数" prop="replyCount">
      <el-input v-model="dataForm.replyCount" placeholder="回复数"></el-input>
    </el-form-item>
    <el-form-item label="评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]" prop="resources">
      <el-input v-model="dataForm.resources" placeholder="评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]"></el-input>
    </el-form-item>
    <el-form-item label="内容" prop="content">
      <el-input v-model="dataForm.content" placeholder="内容"></el-input>
    </el-form-item>
    <el-form-item label="用户头像" prop="memberIcon">
      <el-input v-model="dataForm.memberIcon" placeholder="用户头像"></el-input>
    </el-form-item>
    <el-form-item label="评论类型[0 - 对商品的直接评论，1 - 对评论的回复]" prop="commentType">
      <el-input v-model="dataForm.commentType" placeholder="评论类型[0 - 对商品的直接评论，1 - 对评论的回复]"></el-input>
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
          spuId: '',
          spuName: '',
          memberNickName: '',
          star: '',
          memberIp: '',
          createTime: '',
          showStatus: '',
          spuAttributes: '',
          likesCount: '',
          replyCount: '',
          resources: '',
          content: '',
          memberIcon: '',
          commentType: ''
        },
        dataRule: {
          skuId: [
            { required: true, message: 'sku_id不能为空', trigger: 'blur' }
          ],
          spuId: [
            { required: true, message: 'spu_id不能为空', trigger: 'blur' }
          ],
          spuName: [
            { required: true, message: '商品名字不能为空', trigger: 'blur' }
          ],
          memberNickName: [
            { required: true, message: '会员昵称不能为空', trigger: 'blur' }
          ],
          star: [
            { required: true, message: '星级不能为空', trigger: 'blur' }
          ],
          memberIp: [
            { required: true, message: '会员ip不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          showStatus: [
            { required: true, message: '显示状态[0-不显示，1-显示]不能为空', trigger: 'blur' }
          ],
          spuAttributes: [
            { required: true, message: '购买时属性组合不能为空', trigger: 'blur' }
          ],
          likesCount: [
            { required: true, message: '点赞数不能为空', trigger: 'blur' }
          ],
          replyCount: [
            { required: true, message: '回复数不能为空', trigger: 'blur' }
          ],
          resources: [
            { required: true, message: '评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]不能为空', trigger: 'blur' }
          ],
          content: [
            { required: true, message: '内容不能为空', trigger: 'blur' }
          ],
          memberIcon: [
            { required: true, message: '用户头像不能为空', trigger: 'blur' }
          ],
          commentType: [
            { required: true, message: '评论类型[0 - 对商品的直接评论，1 - 对评论的回复]不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/product/spucomment/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.skuId = data.spuComment.skuId
                this.dataForm.spuId = data.spuComment.spuId
                this.dataForm.spuName = data.spuComment.spuName
                this.dataForm.memberNickName = data.spuComment.memberNickName
                this.dataForm.star = data.spuComment.star
                this.dataForm.memberIp = data.spuComment.memberIp
                this.dataForm.createTime = data.spuComment.createTime
                this.dataForm.showStatus = data.spuComment.showStatus
                this.dataForm.spuAttributes = data.spuComment.spuAttributes
                this.dataForm.likesCount = data.spuComment.likesCount
                this.dataForm.replyCount = data.spuComment.replyCount
                this.dataForm.resources = data.spuComment.resources
                this.dataForm.content = data.spuComment.content
                this.dataForm.memberIcon = data.spuComment.memberIcon
                this.dataForm.commentType = data.spuComment.commentType
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
              url: this.$http.adornUrl(`/product/spucomment/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'skuId': this.dataForm.skuId,
                'spuId': this.dataForm.spuId,
                'spuName': this.dataForm.spuName,
                'memberNickName': this.dataForm.memberNickName,
                'star': this.dataForm.star,
                'memberIp': this.dataForm.memberIp,
                'createTime': this.dataForm.createTime,
                'showStatus': this.dataForm.showStatus,
                'spuAttributes': this.dataForm.spuAttributes,
                'likesCount': this.dataForm.likesCount,
                'replyCount': this.dataForm.replyCount,
                'resources': this.dataForm.resources,
                'content': this.dataForm.content,
                'memberIcon': this.dataForm.memberIcon,
                'commentType': this.dataForm.commentType
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

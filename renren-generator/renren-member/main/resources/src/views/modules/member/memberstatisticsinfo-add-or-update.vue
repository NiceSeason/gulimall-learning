<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="会员id" prop="memberId">
      <el-input v-model="dataForm.memberId" placeholder="会员id"></el-input>
    </el-form-item>
    <el-form-item label="累计消费金额" prop="consumeAmount">
      <el-input v-model="dataForm.consumeAmount" placeholder="累计消费金额"></el-input>
    </el-form-item>
    <el-form-item label="累计优惠金额" prop="couponAmount">
      <el-input v-model="dataForm.couponAmount" placeholder="累计优惠金额"></el-input>
    </el-form-item>
    <el-form-item label="订单数量" prop="orderCount">
      <el-input v-model="dataForm.orderCount" placeholder="订单数量"></el-input>
    </el-form-item>
    <el-form-item label="优惠券数量" prop="couponCount">
      <el-input v-model="dataForm.couponCount" placeholder="优惠券数量"></el-input>
    </el-form-item>
    <el-form-item label="评价数" prop="commentCount">
      <el-input v-model="dataForm.commentCount" placeholder="评价数"></el-input>
    </el-form-item>
    <el-form-item label="退货数量" prop="returnOrderCount">
      <el-input v-model="dataForm.returnOrderCount" placeholder="退货数量"></el-input>
    </el-form-item>
    <el-form-item label="登录次数" prop="loginCount">
      <el-input v-model="dataForm.loginCount" placeholder="登录次数"></el-input>
    </el-form-item>
    <el-form-item label="关注数量" prop="attendCount">
      <el-input v-model="dataForm.attendCount" placeholder="关注数量"></el-input>
    </el-form-item>
    <el-form-item label="粉丝数量" prop="fansCount">
      <el-input v-model="dataForm.fansCount" placeholder="粉丝数量"></el-input>
    </el-form-item>
    <el-form-item label="收藏的商品数量" prop="collectProductCount">
      <el-input v-model="dataForm.collectProductCount" placeholder="收藏的商品数量"></el-input>
    </el-form-item>
    <el-form-item label="收藏的专题活动数量" prop="collectSubjectCount">
      <el-input v-model="dataForm.collectSubjectCount" placeholder="收藏的专题活动数量"></el-input>
    </el-form-item>
    <el-form-item label="收藏的评论数量" prop="collectCommentCount">
      <el-input v-model="dataForm.collectCommentCount" placeholder="收藏的评论数量"></el-input>
    </el-form-item>
    <el-form-item label="邀请的朋友数量" prop="inviteFriendCount">
      <el-input v-model="dataForm.inviteFriendCount" placeholder="邀请的朋友数量"></el-input>
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
          memberId: '',
          consumeAmount: '',
          couponAmount: '',
          orderCount: '',
          couponCount: '',
          commentCount: '',
          returnOrderCount: '',
          loginCount: '',
          attendCount: '',
          fansCount: '',
          collectProductCount: '',
          collectSubjectCount: '',
          collectCommentCount: '',
          inviteFriendCount: ''
        },
        dataRule: {
          memberId: [
            { required: true, message: '会员id不能为空', trigger: 'blur' }
          ],
          consumeAmount: [
            { required: true, message: '累计消费金额不能为空', trigger: 'blur' }
          ],
          couponAmount: [
            { required: true, message: '累计优惠金额不能为空', trigger: 'blur' }
          ],
          orderCount: [
            { required: true, message: '订单数量不能为空', trigger: 'blur' }
          ],
          couponCount: [
            { required: true, message: '优惠券数量不能为空', trigger: 'blur' }
          ],
          commentCount: [
            { required: true, message: '评价数不能为空', trigger: 'blur' }
          ],
          returnOrderCount: [
            { required: true, message: '退货数量不能为空', trigger: 'blur' }
          ],
          loginCount: [
            { required: true, message: '登录次数不能为空', trigger: 'blur' }
          ],
          attendCount: [
            { required: true, message: '关注数量不能为空', trigger: 'blur' }
          ],
          fansCount: [
            { required: true, message: '粉丝数量不能为空', trigger: 'blur' }
          ],
          collectProductCount: [
            { required: true, message: '收藏的商品数量不能为空', trigger: 'blur' }
          ],
          collectSubjectCount: [
            { required: true, message: '收藏的专题活动数量不能为空', trigger: 'blur' }
          ],
          collectCommentCount: [
            { required: true, message: '收藏的评论数量不能为空', trigger: 'blur' }
          ],
          inviteFriendCount: [
            { required: true, message: '邀请的朋友数量不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/member/memberstatisticsinfo/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.memberId = data.memberStatisticsInfo.memberId
                this.dataForm.consumeAmount = data.memberStatisticsInfo.consumeAmount
                this.dataForm.couponAmount = data.memberStatisticsInfo.couponAmount
                this.dataForm.orderCount = data.memberStatisticsInfo.orderCount
                this.dataForm.couponCount = data.memberStatisticsInfo.couponCount
                this.dataForm.commentCount = data.memberStatisticsInfo.commentCount
                this.dataForm.returnOrderCount = data.memberStatisticsInfo.returnOrderCount
                this.dataForm.loginCount = data.memberStatisticsInfo.loginCount
                this.dataForm.attendCount = data.memberStatisticsInfo.attendCount
                this.dataForm.fansCount = data.memberStatisticsInfo.fansCount
                this.dataForm.collectProductCount = data.memberStatisticsInfo.collectProductCount
                this.dataForm.collectSubjectCount = data.memberStatisticsInfo.collectSubjectCount
                this.dataForm.collectCommentCount = data.memberStatisticsInfo.collectCommentCount
                this.dataForm.inviteFriendCount = data.memberStatisticsInfo.inviteFriendCount
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
              url: this.$http.adornUrl(`/member/memberstatisticsinfo/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'memberId': this.dataForm.memberId,
                'consumeAmount': this.dataForm.consumeAmount,
                'couponAmount': this.dataForm.couponAmount,
                'orderCount': this.dataForm.orderCount,
                'couponCount': this.dataForm.couponCount,
                'commentCount': this.dataForm.commentCount,
                'returnOrderCount': this.dataForm.returnOrderCount,
                'loginCount': this.dataForm.loginCount,
                'attendCount': this.dataForm.attendCount,
                'fansCount': this.dataForm.fansCount,
                'collectProductCount': this.dataForm.collectProductCount,
                'collectSubjectCount': this.dataForm.collectSubjectCount,
                'collectCommentCount': this.dataForm.collectCommentCount,
                'inviteFriendCount': this.dataForm.inviteFriendCount
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

<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]" prop="couponType">
      <el-input v-model="dataForm.couponType" placeholder="优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]"></el-input>
    </el-form-item>
    <el-form-item label="优惠券图片" prop="couponImg">
      <el-input v-model="dataForm.couponImg" placeholder="优惠券图片"></el-input>
    </el-form-item>
    <el-form-item label="优惠卷名字" prop="couponName">
      <el-input v-model="dataForm.couponName" placeholder="优惠卷名字"></el-input>
    </el-form-item>
    <el-form-item label="数量" prop="num">
      <el-input v-model="dataForm.num" placeholder="数量"></el-input>
    </el-form-item>
    <el-form-item label="金额" prop="amount">
      <el-input v-model="dataForm.amount" placeholder="金额"></el-input>
    </el-form-item>
    <el-form-item label="每人限领张数" prop="perLimit">
      <el-input v-model="dataForm.perLimit" placeholder="每人限领张数"></el-input>
    </el-form-item>
    <el-form-item label="使用门槛" prop="minPoint">
      <el-input v-model="dataForm.minPoint" placeholder="使用门槛"></el-input>
    </el-form-item>
    <el-form-item label="开始时间" prop="startTime">
      <el-input v-model="dataForm.startTime" placeholder="开始时间"></el-input>
    </el-form-item>
    <el-form-item label="结束时间" prop="endTime">
      <el-input v-model="dataForm.endTime" placeholder="结束时间"></el-input>
    </el-form-item>
    <el-form-item label="使用类型[0->全场通用；1->指定分类；2->指定商品]" prop="useType">
      <el-input v-model="dataForm.useType" placeholder="使用类型[0->全场通用；1->指定分类；2->指定商品]"></el-input>
    </el-form-item>
    <el-form-item label="备注" prop="note">
      <el-input v-model="dataForm.note" placeholder="备注"></el-input>
    </el-form-item>
    <el-form-item label="发行数量" prop="publishCount">
      <el-input v-model="dataForm.publishCount" placeholder="发行数量"></el-input>
    </el-form-item>
    <el-form-item label="已使用数量" prop="useCount">
      <el-input v-model="dataForm.useCount" placeholder="已使用数量"></el-input>
    </el-form-item>
    <el-form-item label="领取数量" prop="receiveCount">
      <el-input v-model="dataForm.receiveCount" placeholder="领取数量"></el-input>
    </el-form-item>
    <el-form-item label="可以领取的开始日期" prop="enableStartTime">
      <el-input v-model="dataForm.enableStartTime" placeholder="可以领取的开始日期"></el-input>
    </el-form-item>
    <el-form-item label="可以领取的结束日期" prop="enableEndTime">
      <el-input v-model="dataForm.enableEndTime" placeholder="可以领取的结束日期"></el-input>
    </el-form-item>
    <el-form-item label="优惠码" prop="code">
      <el-input v-model="dataForm.code" placeholder="优惠码"></el-input>
    </el-form-item>
    <el-form-item label="可以领取的会员等级[0->不限等级，其他-对应等级]" prop="memberLevel">
      <el-input v-model="dataForm.memberLevel" placeholder="可以领取的会员等级[0->不限等级，其他-对应等级]"></el-input>
    </el-form-item>
    <el-form-item label="发布状态[0-未发布，1-已发布]" prop="publish">
      <el-input v-model="dataForm.publish" placeholder="发布状态[0-未发布，1-已发布]"></el-input>
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
          couponType: '',
          couponImg: '',
          couponName: '',
          num: '',
          amount: '',
          perLimit: '',
          minPoint: '',
          startTime: '',
          endTime: '',
          useType: '',
          note: '',
          publishCount: '',
          useCount: '',
          receiveCount: '',
          enableStartTime: '',
          enableEndTime: '',
          code: '',
          memberLevel: '',
          publish: ''
        },
        dataRule: {
          couponType: [
            { required: true, message: '优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]不能为空', trigger: 'blur' }
          ],
          couponImg: [
            { required: true, message: '优惠券图片不能为空', trigger: 'blur' }
          ],
          couponName: [
            { required: true, message: '优惠卷名字不能为空', trigger: 'blur' }
          ],
          num: [
            { required: true, message: '数量不能为空', trigger: 'blur' }
          ],
          amount: [
            { required: true, message: '金额不能为空', trigger: 'blur' }
          ],
          perLimit: [
            { required: true, message: '每人限领张数不能为空', trigger: 'blur' }
          ],
          minPoint: [
            { required: true, message: '使用门槛不能为空', trigger: 'blur' }
          ],
          startTime: [
            { required: true, message: '开始时间不能为空', trigger: 'blur' }
          ],
          endTime: [
            { required: true, message: '结束时间不能为空', trigger: 'blur' }
          ],
          useType: [
            { required: true, message: '使用类型[0->全场通用；1->指定分类；2->指定商品]不能为空', trigger: 'blur' }
          ],
          note: [
            { required: true, message: '备注不能为空', trigger: 'blur' }
          ],
          publishCount: [
            { required: true, message: '发行数量不能为空', trigger: 'blur' }
          ],
          useCount: [
            { required: true, message: '已使用数量不能为空', trigger: 'blur' }
          ],
          receiveCount: [
            { required: true, message: '领取数量不能为空', trigger: 'blur' }
          ],
          enableStartTime: [
            { required: true, message: '可以领取的开始日期不能为空', trigger: 'blur' }
          ],
          enableEndTime: [
            { required: true, message: '可以领取的结束日期不能为空', trigger: 'blur' }
          ],
          code: [
            { required: true, message: '优惠码不能为空', trigger: 'blur' }
          ],
          memberLevel: [
            { required: true, message: '可以领取的会员等级[0->不限等级，其他-对应等级]不能为空', trigger: 'blur' }
          ],
          publish: [
            { required: true, message: '发布状态[0-未发布，1-已发布]不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/coupon/coupon/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.couponType = data.coupon.couponType
                this.dataForm.couponImg = data.coupon.couponImg
                this.dataForm.couponName = data.coupon.couponName
                this.dataForm.num = data.coupon.num
                this.dataForm.amount = data.coupon.amount
                this.dataForm.perLimit = data.coupon.perLimit
                this.dataForm.minPoint = data.coupon.minPoint
                this.dataForm.startTime = data.coupon.startTime
                this.dataForm.endTime = data.coupon.endTime
                this.dataForm.useType = data.coupon.useType
                this.dataForm.note = data.coupon.note
                this.dataForm.publishCount = data.coupon.publishCount
                this.dataForm.useCount = data.coupon.useCount
                this.dataForm.receiveCount = data.coupon.receiveCount
                this.dataForm.enableStartTime = data.coupon.enableStartTime
                this.dataForm.enableEndTime = data.coupon.enableEndTime
                this.dataForm.code = data.coupon.code
                this.dataForm.memberLevel = data.coupon.memberLevel
                this.dataForm.publish = data.coupon.publish
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
              url: this.$http.adornUrl(`/coupon/coupon/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'couponType': this.dataForm.couponType,
                'couponImg': this.dataForm.couponImg,
                'couponName': this.dataForm.couponName,
                'num': this.dataForm.num,
                'amount': this.dataForm.amount,
                'perLimit': this.dataForm.perLimit,
                'minPoint': this.dataForm.minPoint,
                'startTime': this.dataForm.startTime,
                'endTime': this.dataForm.endTime,
                'useType': this.dataForm.useType,
                'note': this.dataForm.note,
                'publishCount': this.dataForm.publishCount,
                'useCount': this.dataForm.useCount,
                'receiveCount': this.dataForm.receiveCount,
                'enableStartTime': this.dataForm.enableStartTime,
                'enableEndTime': this.dataForm.enableEndTime,
                'code': this.dataForm.code,
                'memberLevel': this.dataForm.memberLevel,
                'publish': this.dataForm.publish
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

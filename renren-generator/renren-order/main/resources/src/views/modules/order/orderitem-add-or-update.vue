<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="order_id" prop="orderId">
      <el-input v-model="dataForm.orderId" placeholder="order_id"></el-input>
    </el-form-item>
    <el-form-item label="order_sn" prop="orderSn">
      <el-input v-model="dataForm.orderSn" placeholder="order_sn"></el-input>
    </el-form-item>
    <el-form-item label="spu_id" prop="spuId">
      <el-input v-model="dataForm.spuId" placeholder="spu_id"></el-input>
    </el-form-item>
    <el-form-item label="spu_name" prop="spuName">
      <el-input v-model="dataForm.spuName" placeholder="spu_name"></el-input>
    </el-form-item>
    <el-form-item label="spu_pic" prop="spuPic">
      <el-input v-model="dataForm.spuPic" placeholder="spu_pic"></el-input>
    </el-form-item>
    <el-form-item label="品牌" prop="spuBrand">
      <el-input v-model="dataForm.spuBrand" placeholder="品牌"></el-input>
    </el-form-item>
    <el-form-item label="商品分类id" prop="categoryId">
      <el-input v-model="dataForm.categoryId" placeholder="商品分类id"></el-input>
    </el-form-item>
    <el-form-item label="商品sku编号" prop="skuId">
      <el-input v-model="dataForm.skuId" placeholder="商品sku编号"></el-input>
    </el-form-item>
    <el-form-item label="商品sku名字" prop="skuName">
      <el-input v-model="dataForm.skuName" placeholder="商品sku名字"></el-input>
    </el-form-item>
    <el-form-item label="商品sku图片" prop="skuPic">
      <el-input v-model="dataForm.skuPic" placeholder="商品sku图片"></el-input>
    </el-form-item>
    <el-form-item label="商品sku价格" prop="skuPrice">
      <el-input v-model="dataForm.skuPrice" placeholder="商品sku价格"></el-input>
    </el-form-item>
    <el-form-item label="商品购买的数量" prop="skuQuantity">
      <el-input v-model="dataForm.skuQuantity" placeholder="商品购买的数量"></el-input>
    </el-form-item>
    <el-form-item label="商品销售属性组合（JSON）" prop="skuAttrsVals">
      <el-input v-model="dataForm.skuAttrsVals" placeholder="商品销售属性组合（JSON）"></el-input>
    </el-form-item>
    <el-form-item label="商品促销分解金额" prop="promotionAmount">
      <el-input v-model="dataForm.promotionAmount" placeholder="商品促销分解金额"></el-input>
    </el-form-item>
    <el-form-item label="优惠券优惠分解金额" prop="couponAmount">
      <el-input v-model="dataForm.couponAmount" placeholder="优惠券优惠分解金额"></el-input>
    </el-form-item>
    <el-form-item label="积分优惠分解金额" prop="integrationAmount">
      <el-input v-model="dataForm.integrationAmount" placeholder="积分优惠分解金额"></el-input>
    </el-form-item>
    <el-form-item label="该商品经过优惠后的分解金额" prop="realAmount">
      <el-input v-model="dataForm.realAmount" placeholder="该商品经过优惠后的分解金额"></el-input>
    </el-form-item>
    <el-form-item label="赠送积分" prop="giftIntegration">
      <el-input v-model="dataForm.giftIntegration" placeholder="赠送积分"></el-input>
    </el-form-item>
    <el-form-item label="赠送成长值" prop="giftGrowth">
      <el-input v-model="dataForm.giftGrowth" placeholder="赠送成长值"></el-input>
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
          orderId: '',
          orderSn: '',
          spuId: '',
          spuName: '',
          spuPic: '',
          spuBrand: '',
          categoryId: '',
          skuId: '',
          skuName: '',
          skuPic: '',
          skuPrice: '',
          skuQuantity: '',
          skuAttrsVals: '',
          promotionAmount: '',
          couponAmount: '',
          integrationAmount: '',
          realAmount: '',
          giftIntegration: '',
          giftGrowth: ''
        },
        dataRule: {
          orderId: [
            { required: true, message: 'order_id不能为空', trigger: 'blur' }
          ],
          orderSn: [
            { required: true, message: 'order_sn不能为空', trigger: 'blur' }
          ],
          spuId: [
            { required: true, message: 'spu_id不能为空', trigger: 'blur' }
          ],
          spuName: [
            { required: true, message: 'spu_name不能为空', trigger: 'blur' }
          ],
          spuPic: [
            { required: true, message: 'spu_pic不能为空', trigger: 'blur' }
          ],
          spuBrand: [
            { required: true, message: '品牌不能为空', trigger: 'blur' }
          ],
          categoryId: [
            { required: true, message: '商品分类id不能为空', trigger: 'blur' }
          ],
          skuId: [
            { required: true, message: '商品sku编号不能为空', trigger: 'blur' }
          ],
          skuName: [
            { required: true, message: '商品sku名字不能为空', trigger: 'blur' }
          ],
          skuPic: [
            { required: true, message: '商品sku图片不能为空', trigger: 'blur' }
          ],
          skuPrice: [
            { required: true, message: '商品sku价格不能为空', trigger: 'blur' }
          ],
          skuQuantity: [
            { required: true, message: '商品购买的数量不能为空', trigger: 'blur' }
          ],
          skuAttrsVals: [
            { required: true, message: '商品销售属性组合（JSON）不能为空', trigger: 'blur' }
          ],
          promotionAmount: [
            { required: true, message: '商品促销分解金额不能为空', trigger: 'blur' }
          ],
          couponAmount: [
            { required: true, message: '优惠券优惠分解金额不能为空', trigger: 'blur' }
          ],
          integrationAmount: [
            { required: true, message: '积分优惠分解金额不能为空', trigger: 'blur' }
          ],
          realAmount: [
            { required: true, message: '该商品经过优惠后的分解金额不能为空', trigger: 'blur' }
          ],
          giftIntegration: [
            { required: true, message: '赠送积分不能为空', trigger: 'blur' }
          ],
          giftGrowth: [
            { required: true, message: '赠送成长值不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/order/orderitem/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.orderId = data.orderItem.orderId
                this.dataForm.orderSn = data.orderItem.orderSn
                this.dataForm.spuId = data.orderItem.spuId
                this.dataForm.spuName = data.orderItem.spuName
                this.dataForm.spuPic = data.orderItem.spuPic
                this.dataForm.spuBrand = data.orderItem.spuBrand
                this.dataForm.categoryId = data.orderItem.categoryId
                this.dataForm.skuId = data.orderItem.skuId
                this.dataForm.skuName = data.orderItem.skuName
                this.dataForm.skuPic = data.orderItem.skuPic
                this.dataForm.skuPrice = data.orderItem.skuPrice
                this.dataForm.skuQuantity = data.orderItem.skuQuantity
                this.dataForm.skuAttrsVals = data.orderItem.skuAttrsVals
                this.dataForm.promotionAmount = data.orderItem.promotionAmount
                this.dataForm.couponAmount = data.orderItem.couponAmount
                this.dataForm.integrationAmount = data.orderItem.integrationAmount
                this.dataForm.realAmount = data.orderItem.realAmount
                this.dataForm.giftIntegration = data.orderItem.giftIntegration
                this.dataForm.giftGrowth = data.orderItem.giftGrowth
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
              url: this.$http.adornUrl(`/order/orderitem/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'orderId': this.dataForm.orderId,
                'orderSn': this.dataForm.orderSn,
                'spuId': this.dataForm.spuId,
                'spuName': this.dataForm.spuName,
                'spuPic': this.dataForm.spuPic,
                'spuBrand': this.dataForm.spuBrand,
                'categoryId': this.dataForm.categoryId,
                'skuId': this.dataForm.skuId,
                'skuName': this.dataForm.skuName,
                'skuPic': this.dataForm.skuPic,
                'skuPrice': this.dataForm.skuPrice,
                'skuQuantity': this.dataForm.skuQuantity,
                'skuAttrsVals': this.dataForm.skuAttrsVals,
                'promotionAmount': this.dataForm.promotionAmount,
                'couponAmount': this.dataForm.couponAmount,
                'integrationAmount': this.dataForm.integrationAmount,
                'realAmount': this.dataForm.realAmount,
                'giftIntegration': this.dataForm.giftIntegration,
                'giftGrowth': this.dataForm.giftGrowth
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

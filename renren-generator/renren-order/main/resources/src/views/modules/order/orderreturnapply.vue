<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key" placeholder="参数名" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button v-if="isAuth('order:orderreturnapply:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button v-if="isAuth('order:orderreturnapply:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
      <el-table-column
        prop="id"
        header-align="center"
        align="center"
        label="id">
      </el-table-column>
      <el-table-column
        prop="orderId"
        header-align="center"
        align="center"
        label="order_id">
      </el-table-column>
      <el-table-column
        prop="skuId"
        header-align="center"
        align="center"
        label="退货商品id">
      </el-table-column>
      <el-table-column
        prop="orderSn"
        header-align="center"
        align="center"
        label="订单编号">
      </el-table-column>
      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        label="申请时间">
      </el-table-column>
      <el-table-column
        prop="memberUsername"
        header-align="center"
        align="center"
        label="会员用户名">
      </el-table-column>
      <el-table-column
        prop="returnAmount"
        header-align="center"
        align="center"
        label="退款金额">
      </el-table-column>
      <el-table-column
        prop="returnName"
        header-align="center"
        align="center"
        label="退货人姓名">
      </el-table-column>
      <el-table-column
        prop="returnPhone"
        header-align="center"
        align="center"
        label="退货人电话">
      </el-table-column>
      <el-table-column
        prop="status"
        header-align="center"
        align="center"
        label="申请状态[0->待处理；1->退货中；2->已完成；3->已拒绝]">
      </el-table-column>
      <el-table-column
        prop="handleTime"
        header-align="center"
        align="center"
        label="处理时间">
      </el-table-column>
      <el-table-column
        prop="skuImg"
        header-align="center"
        align="center"
        label="商品图片">
      </el-table-column>
      <el-table-column
        prop="skuName"
        header-align="center"
        align="center"
        label="商品名称">
      </el-table-column>
      <el-table-column
        prop="skuBrand"
        header-align="center"
        align="center"
        label="商品品牌">
      </el-table-column>
      <el-table-column
        prop="skuAttrsVals"
        header-align="center"
        align="center"
        label="商品销售属性(JSON)">
      </el-table-column>
      <el-table-column
        prop="skuCount"
        header-align="center"
        align="center"
        label="退货数量">
      </el-table-column>
      <el-table-column
        prop="skuPrice"
        header-align="center"
        align="center"
        label="商品单价">
      </el-table-column>
      <el-table-column
        prop="skuRealPrice"
        header-align="center"
        align="center"
        label="商品实际支付单价">
      </el-table-column>
      <el-table-column
        prop="reason"
        header-align="center"
        align="center"
        label="原因">
      </el-table-column>
      <el-table-column
        prop="description述"
        header-align="center"
        align="center"
        label="描述">
      </el-table-column>
      <el-table-column
        prop="descPics"
        header-align="center"
        align="center"
        label="凭证图片，以逗号隔开">
      </el-table-column>
      <el-table-column
        prop="handleNote"
        header-align="center"
        align="center"
        label="处理备注">
      </el-table-column>
      <el-table-column
        prop="handleMan"
        header-align="center"
        align="center"
        label="处理人员">
      </el-table-column>
      <el-table-column
        prop="receiveMan"
        header-align="center"
        align="center"
        label="收货人">
      </el-table-column>
      <el-table-column
        prop="receiveTime"
        header-align="center"
        align="center"
        label="收货时间">
      </el-table-column>
      <el-table-column
        prop="receiveNote"
        header-align="center"
        align="center"
        label="收货备注">
      </el-table-column>
      <el-table-column
        prop="receivePhone"
        header-align="center"
        align="center"
        label="收货电话">
      </el-table-column>
      <el-table-column
        prop="companyAddress"
        header-align="center"
        align="center"
        label="公司收货地址">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
  import AddOrUpdate from './orderreturnapply-add-or-update'
  export default {
    data () {
      return {
        dataForm: {
          key: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false
      }
    },
    components: {
      AddOrUpdate
    },
    activated () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/order/orderreturnapply/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'key': this.dataForm.key
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.page.list
            this.totalPage = data.page.totalCount
          } else {
            this.dataList = []
            this.totalPage = 0
          }
          this.dataListLoading = false
        })
      },
      // 每页数
      sizeChangeHandle (val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataList()
      },
      // 当前页
      currentChangeHandle (val) {
        this.pageIndex = val
        this.getDataList()
      },
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val
      },
      // 新增 / 修改
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },
      // 删除
      deleteHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.id
        })
        this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/order/orderreturnapply/delete'),
            method: 'post',
            data: this.$http.adornData(ids, false)
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        })
      }
    }
  }
</script>

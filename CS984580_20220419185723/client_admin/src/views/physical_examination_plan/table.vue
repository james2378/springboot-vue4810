<template>
	<el-main class="bg table_wrap">
		<el-form label-position="right" :model="query" class="form p_4" label-width="120">
			<el-row>
				<el-col :xs="24" :sm="24" :lg="8" class="el_form_search_wrap">
					<el-form-item label="体检人员">
						<el-select v-model="query.physical_examination_personnel">
							<el-option v-for="o in list_physical_examination_personnel" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
					</el-form-item>
				</el-col>
				<el-col :xs="24" :sm="24" :lg="8" class="el_form_search_wrap">
					<el-form-item label="姓名">
						<el-input v-model="query.full_name"></el-input>
					</el-form-item>
				</el-col>
				<el-col :xs="24" :sm="24" :lg="8" class="el_form_search_wrap">
					<el-form-item label="套餐名称">
						<el-input v-model="query.package_name"></el-input>
					</el-form-item>
				</el-col>
				<el-col :xs="24" :sm="24" :lg="8" class="el_form_search_wrap">
					<el-form-item label="预约日期">
						<el-input v-model="query.appointment_date"></el-input>
					</el-form-item>
				</el-col>
				<el-col :xs="24" :sm="10" :lg="8" class="search_btn_wrap_1">
					<el-form-item>
						<el-button type="primary" @click="search()" class="search_btn_find">查询</el-button>
						<el-button @click="reset()" style="margin-right: 74px;" class="search_btn_reset">重置</el-button>
						<router-link v-if="user_group == '管理员' || $check_action('/physical_examination_plan/table','add') || $check_action('/physical_examination_plan/view','add')" class="el-button el-button--default el-button--primary search_btn_add" to="./view?">添加
						</router-link>
            <el-button v-if="user_group == '管理员' || $check_action('/physical_examination_plan/table','del') || $check_action('/physical_examination_plan/view','del')" class="search_btn_del" type="danger" @click="delInfo()">删除</el-button>
					</el-form-item>
				</el-col>

			</el-row>
		</el-form>
		<el-table :data="list" @selection-change="selectionChange" @sort-change="$sortChange" style="width: 100%" id="dataTable">
			<el-table-column fixed type="selection" tooltip-effect="dark" width="55">
			</el-table-column>
			<el-table-column prop="physical_examination_personnel" label="体检人员"
				v-if="user_group == '管理员' || $check_field('get','physical_examination_personnel')" min-width="200">
				<template slot-scope="scope">
					{{ get_user_physical_examination_personnel(scope.row['physical_examination_personnel']) }}
				</template>
			</el-table-column>
			<el-table-column prop="full_name" label="姓名"
				v-if="user_group == '管理员' || $check_field('get','full_name')" min-width="200">
			</el-table-column>
			<el-table-column prop="gender" label="性别"
				v-if="user_group == '管理员' || $check_field('get','gender')" min-width="200">
			</el-table-column>
			<el-table-column prop="age" label="年龄"
				v-if="user_group == '管理员' || $check_field('get','age')" min-width="200">
			</el-table-column>
			<el-table-column prop="package_name" label="套餐名称"
				v-if="user_group == '管理员' || $check_field('get','package_name')" min-width="200">
			</el-table-column>
			<el-table-column prop="package_fee" label="套餐费用"
				v-if="user_group == '管理员' || $check_field('get','package_fee')" min-width="200">
			</el-table-column>
			<el-table-column prop="appointment_date" label="预约日期"
				v-if="user_group == '管理员' || $check_field('get','appointment_date')" min-width="200">
                <template slot-scope="scope">
                	{{ $toTime(scope.row["appointment_date"],"yyyy-MM-dd") }}
                </template>
			</el-table-column>
			<el-table-column prop="details_of_physical_examination_items" label="体检项目详情"
				v-if="user_group == '管理员' || $check_field('get','details_of_physical_examination_items')" min-width="200">
			</el-table-column>
			<el-table-column label="审核状态" prop="examine_state">
			</el-table-column>
			<el-table-column label="审核回复" prop="examine_reply">
			</el-table-column>
			<el-table-column label="支付状态" prop="pay_state">
			</el-table-column>

			<el-table-column label="支付类型" prop="pay_type">
			</el-table-column>

            <el-table-column sortable prop="create_time" label="创建时间" min-width="200">
                <template slot-scope="scope">
                	{{ $toTime(scope.row["create_time"],"yyyy-MM-dd hh:mm:ss") }}
                </template>
            </el-table-column>

			<el-table-column sortable prop="update_time" label="更新时间" min-width="200">
                <template slot-scope="scope">
                	{{ $toTime(scope.row["update_time"],"yyyy-MM-dd hh:mm:ss") }}
                </template>
			</el-table-column>
			<el-table-column fixed="right" label="操作" min-width="120" v-if="user_group == '管理员' || $check_action('/physical_examination_plan/table','set') || $check_action('/physical_examination_plan/view','set') || $check_action('/physical_examination_plan/view','get') || $check_action('/填写档案/table','add') || $check_action('/填写档案/view','add')" >


				<template slot-scope="scope">
					<router-link class="el-button el-button--small is-plain el-button--success" style="margin: 5px !important;"
					v-if="user_group == '管理员' || $check_action('/physical_examination_plan/table','set') || $check_action('/physical_examination_plan/view','set') || $check_action('/physical_examination_plan/view','get')"
						:to="'./view?' + field + '=' + scope.row[field]"
						 size="small">
						<span>详情</span>
					</router-link>
					<!--跨表按钮-->
					<el-button class="el-button el-button--small is-plain el-button--default" style="margin: 5px !important;" size="small" @click="to_table(scope.row,'/health_records/view')" v-if="user_group == '管理员' || $check_action('/health_records/table','add') || $check_action('/health_records/view','add')">
						<span>填写档案</span>
					</el-button>
					<el-button class="el-button el-button--small is-plain el-button--default" style="margin: 5px !important;" size="small" @click="openPayModal(scope.row)" v-if="scope.row.pay_state==='未支付' && (user_group == '管理员' || $check_pay('/physical_examination_plan/table'))">
						<span>支付</span>
					</el-button>
				</template>
			</el-table-column>

		</el-table>

		<!-- 分页器 -->
		<div class="mt text_center">
			<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
				:current-page="query.page" :page-sizes="[7, 10, 30, 100]" :page-size="query.size"
				layout="total, sizes, prev, pager, next, jumper" :total="count">
			</el-pagination>
		</div>
		<!-- /分页器 -->
		<el-dialog
				title="支付"
				:visible.sync="payModalVisible"
				width="500px"
		>
			<el-tabs v-model="pay_obj.payActiveName" :stretch="true">
				<el-tab-pane label="微信" name="微信">
					<div style="width: 80%;margin: 0 auto;">
						<div style="width: 60%;margin: 30px auto;">
							<el-image style="width: 100%;" :src="$fullUrl('/api/upload/wx.png')"
									  :preview-src-list="[$fullUrl('/api/upload/wx.png')]">
								<div slot="error" class="image-slot">
									<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
								</div>
							</el-image>
						</div>
						<div style="width: 80%;margin: 0 auto;">
							<div style="width: 100%;background-color: #409EFF;color: white;font-size: 14px;text-align: center;line-height: 40px;cursor: pointer" @click="submitPay()">确认支付</div>
						</div>
					</div>
				</el-tab-pane>
				<el-tab-pane label="支付宝" name="支付宝">
					<div style="width: 80%;margin: 0 auto;">
						<div style="width: 60%;margin: 30px auto;">
							<el-image style="width: 100%;" :src="$fullUrl('/api/upload/aplipay.png')"
									  :preview-src-list="[$fullUrl('/api/upload/aplipay.png')]">
								<div slot="error" class="image-slot">
									<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
								</div>
							</el-image>
						</div>
						<div style="width: 80%;margin: 0 auto;">
							<div style="width: 100%;background-color: #409EFF;color: white;font-size: 14px;text-align: center;line-height: 40px;cursor: pointer" @click="submitPay()">确认支付</div>
						</div>
					</div>
				</el-tab-pane>
				<el-tab-pane label="网银" name="网银">
					<div style="width: 80%;margin: 0 auto;margin-top: 30px;">
						<div style="width: 80%;margin: 0 auto;">
							<div style="line-height: 40px">请输入网银账号：</div>
						</div>
						<div style="width: 80%;margin: 0 auto;">
							<el-input v-model="pay_obj.account" placeholder="请输入网银账号"></el-input>
						</div>
					</div>
					<div style="width: 80%;margin: 0 auto;margin-top: 20px;">
						<div style="width: 80%;margin: 0 auto;">
							<div style="line-height: 40px">请输入支付密码，6位数字：</div>
						</div>
						<div style="width: 80%;margin: 0 auto;">
							<el-input placeholder="请输入密码" v-model="pay_obj.password" show-password maxlength="6"></el-input>
						</div>
					</div>
					<div style="width: 80%;margin: 0 auto;margin-top: 40px;">
						<div style="width: 100%;background-color: #409EFF;color: white;font-size: 14px;text-align: center;line-height: 40px;cursor: pointer" @click="submitPay()">确认支付</div>
					</div>
				</el-tab-pane>
			</el-tabs>
		</el-dialog>

		<div class="modal_wrap" v-if="showModal">
			<div class="modal_box">
				<!-- <div class="modal_box_close" @click="closeModal">X</div> -->
				<p class="modal_box_title">重要提醒</p>
				<p class="modal_box_text">当前有数据达到预警值！</p>
				<div class="btn_box">
					<span @click="closeModal">取消</span>
					<span @click="closeModal">确定</span>
				</div>
			</div>
		</div>


	</el-main>
</template>
<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				// 弹框
				showModal: false,
				// 获取数据地址
				url_get_list: "~/api/physical_examination_plan/get_list?like=0",
				url_del: "~/api/physical_examination_plan/del?",

				// 字段ID
				field: "physical_examination_plan_id",

				// 查询
				query: {
					"size": 7,
					"page": 1,
					"physical_examination_personnel": "",
					"full_name": "",
					"package_name": "",
					"appointment_date": "",
					"login_time": "",
					"create_time": "",
					"orderby": `create_time desc`
				},
				// 数据
				list: [],
				payModalVisible:false,
				pay_obj:{
					payActiveName:"微信",
					account:"",
					password:"",
					id:""
				},
				list_physical_examination_personnel: [],
				// 用户列表
				list_user_physical_examination_personnel: [],
				// 性别列表
				list_gender: ['男','女'],
			}
		},
		methods: {
			// 关闭弹框
			closeModal(){
				this.showModal = false;
				},
			get_list_before(param){
				var user_group = this.user.user_group;
				if(user_group != "管理员"){
					switch(user_group){
						case "体检人员":
						param["physical_examination_personnel"] = this.user.user_id;
						break;
					}
				}
				return param;
			},
			/**
			 * 获取体检人员列表
			 */
			async get_list_physical_examination_personnel() {
				var json = await this.$get("~/api/user/get_list?user_group=体检人员");
				if(json.result && json.result.list){
					this.list_physical_examination_personnel = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},

			open_tip() {
				const h = this.$createElement;

				var message = "";
				var list = this.list;

				var ifs = [ ];
				for (var n = 0; n < ifs.length; n++) {
					var o = ifs[n];
					for (var i = 0; i < list.length; i++) {
						var lt = list[i];
						if (o.type == "数内") {
							if ((o.start || o.start === 0) && (o.end || o.end === 0)) {
								if (lt[o.factor] > o.start && lt[o.factor] < o.end) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.start || o.start === 0) {
								if (lt[o.factor] > o.start) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.end || o.end === 0) {
								if (lt[o.factor] < o.end) {
									o["idx"] = o["idx"] + 1;
								}
							}
						} else if (o.type == "数外") {
							if ((o.start || o.start === 0) && (o.end || o.end === 0)) {
								if (lt[o.factor] < o.start || lt[o.factor] > o.end) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.start || o.start === 0) {
								if (lt[o.factor] < o.start) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.end || o.end === 0) {
								if (lt[o.factor] > o.end) {
									o["idx"] = o["idx"] + 1;
								}
							}
						} else if (o.type == "日内") {
							if ((o.start) && (o.end)) {
								if (lt[o.factor] > o.start && lt[o.factor] < o.end) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.start) {
								if (lt[o.factor] < o.start) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.end) {
								if (lt[o.factor] > o.end) {
									o["idx"] = o["idx"] + 1;
								}
							}
						} else if (o.type == "日外") {
							if (o.start && o.end) {
								if (lt[o.factor] < o.start || lt[o.factor] > o.end) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.start) {
								if (lt[o.factor] < o.start) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.end) {
								if (lt[o.factor] > o.end) {
									o["idx"] = o["idx"] + 1;
								}
							}
						}
					}

					if (o["idx"]) {
						message += o.title;
						if (o["type"] == "数内") {
							if (o.start || o.start === 0) {
								message += "大于" + o.start;
							}
							if ((o.start || o.start === 0) && (o.end || o.end === 0)) {
								message += "并且";
							}
							if (o.end || o.end === 0) {
								message += "小于" + o.end;
							}
						} else if (o["type"] == "数外") {
							if (o.start || o.start === 0) {
								message += "小于" + o.start;
							}
							if ((o.start || o.start === 0) || (o.end || o.end === 0)) {
								message += "或者";
							}
							if (o.end || o.end === 0) {
								message += "大于" + o.end;
							}
						} else if (o["type"] == "日内") {
							if (o.start) {
								message += "在" + o.start + "之后";
							}
							if (o.start && o.end) {
								message += "并且";
							}
							if (o.end) {
								message += "在" + o.end + "之前";
							}
						} else if (o["type"] == "日外") {
							if (o.start) {
								message += "在" + o.start + "之前";
							}
							if (o.start || o.end) {
								message += "或者";
							}
							if (o.end) {
								message += "在" + o.end + "之后";
							}
						}
						message += "的有" + o["idx"] + "条";
					}
				}

				if (message) {
					message += "，需要处理、请尽快处理。";
					this.showModal = true;
					// this.$notify({
					// 	title: '提醒',
					// 	dangerouslyUseHTMLString: true,
					// 	message: h('i', {
					// 		style: 'color: teal'
					// 	}, message)
					// });
				}
			},
			/**
			 * 获取体检人员用户列表
			 */
			async get_list_user_physical_examination_personnel() {
				var json = await this.$get("~/api/user/get_list?user_group=体检人员");
				if(json.result && json.result.list){
					this.list_user_physical_examination_personnel = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},

			get_user_physical_examination_personnel(id){
				var obj = this.list_user_physical_examination_personnel.getObj({"user_id":id});
				var ret = "";
				if(obj){
					ret = obj.nickname+"-"+obj.username;
					// if(obj.nickname){
					// 	ret = obj.nickname;
					// }
					// else{
					// 	ret = obj.username;
					// }
				}
				return ret;
			},
			openPayModal(v){
				this.pay_obj.id = v.physical_examination_plan_id
				this.payModalVisible = true
			},
			submitPay(){
				let _this = this;
				let url = this.$toUrl(this.query, "~/api/physical_examination_plan/set?physical_examination_plan_id="+this.pay_obj.id);
				let param = {"pay_state":"已支付","pay_type":this.pay_obj.payActiveName}
				this.$post(url, param, function(json, status) {
					console.log("提交结果：" ,json);
					if (json.result) {
						_this.$toast('支付成功！', 'success');
						_this.payModalVisible = false;
						_this.get_list();
					} else if (json.error) {
						_this.$toast(json.error.message, 'danger');
					}
				});
			},
			deleteRow(index, rows) {
				rows.splice(index, 1);
			}

		},
		created() {
			this.get_list_physical_examination_personnel();
			this.get_list_user_physical_examination_personnel();
			setTimeout(() => {
				this.open_tip();
			}, 1000)
		}
	}
</script>

<style type="text/css">
	.bg {
		background: white;
	}

	.form.p_4 {
		padding: 1rem;
	}

	.form .el-input {
		width: initial;
	}

	.mt {
		margin-top: 1rem;
	}

	.text_center {
		text-align: center;
	}

	.float-right {
		float: right;
	}


	.modal_wrap{
		width: 100vw;
		height: 100vh;
		position: fixed;
		top: 0;
		left: 0;
		background: rgba(0,0,0,0.5);
		z-index: 9999999999;
	}
	.modal_wrap .modal_box{
		width: 400px;
		height: 200px;
		background: url("../../assets/modal_bg.jpg") no-repeat center;
		background-size: cover;
		position: absolute;
		top: 50%;
		left: 50%;
		margin-left: -200px;
		margin-top: -100px;
		border-radius: 10px;
		}
	.modal_wrap .modal_box .modal_box_close{
		font-size: 20px;
		position: absolute;
		top: 10px;
		right: 10px;
		cursor: pointer;
		}
	.modal_wrap .modal_box .modal_box_title{
	  text-align: center;
    font-size: 18px;
    margin: 16px auto;
    color: #fff;
    border-bottom: 1px solid rgba(117, 116, 116,0.5);
    padding-bottom: 16px;
    width: 356px;
		}
	.modal_wrap .modal_box .modal_box_text{
			text-align: center;
		font-size: 15px;
		color: #fff;
		margin-top: 25px;
		}
	.modal_wrap .modal_box .btn_box{
		display: flex;
		flex-direction: row;
		justify-content: center;
		margin-top: 42px;
		}
			.modal_wrap .modal_box .btn_box span{
				display: inline-block;
				width: 80px;
				height: 30px;
				line-height: 30px;
				text-align: center;
				border: 1px solid #ccc;
				font-size: 14px;
				cursor: pointer;
				color: #fff;
			}
	.modal_wrap .modal_box .btn_box span:nth-child(2){
		background: #409EFF;
		color: #fff;
		border-color: #409EFF;
		margin-left: 15px;
	}
</style>

<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','physical_examination_personnel') || $check_field('add','physical_examination_personnel') || $check_field('set','physical_examination_personnel')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="体检人员" prop="physical_examination_personnel">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_physical_examination_personnel(form['physical_examination_personnel']) }}
							<!--<el-input id="business_name" v-model="form['physical_examination_personnel']" placeholder="请输入体检人员"-->
							<!--v-if="user_group === '管理员' || (form['physical_examination_plan_id'] && $check_field('set','physical_examination_personnel')) || (!form['physical_examination_plan_id'] && $check_field('add','physical_examination_personnel'))" :disabled="disabledObj['physical_examination_personnel_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','physical_examination_personnel')">{{form['physical_examination_personnel']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['physical_examination_plan_id'] && $check_field('set','physical_examination_personnel')) || (!form['physical_examination_plan_id'] && $check_field('add','physical_examination_personnel'))" id="physical_examination_personnel" v-model="form['physical_examination_personnel']" :disabled="disabledObj['physical_examination_personnel_isDisabled']">
								<el-option v-for="o in list_user_physical_examination_personnel" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','physical_examination_personnel')" id="physical_examination_personnel" v-model="form['physical_examination_personnel']" :disabled="true">
								<el-option v-for="o in list_user_physical_examination_personnel" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="physical_examination_personnel" v-model="form['physical_examination_personnel']" :disabled="disabledObj['physical_examination_personnel_isDisabled']">
							<el-option v-for="o in list_user_physical_examination_personnel" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','full_name') || $check_field('add','full_name') || $check_field('set','full_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="姓名" prop="full_name">
					<el-input id="full_name" v-model="form['full_name']" placeholder="请输入姓名"
							  v-if="user_group === '管理员' || (form['physical_examination_plan_id'] && $check_field('set','full_name')) || (!form['physical_examination_plan_id'] && $check_field('add','full_name'))" :disabled="disabledObj['full_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','full_name')">{{form['full_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','gender') || $check_field('add','gender') || $check_field('set','gender')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="性别" prop="gender">
					<el-select id="gender" v-model="form['gender']"
						v-if="user_group === '管理员' || (form['physical_examination_plan_id'] && $check_field('set','gender')) || (!form['physical_examination_plan_id'] && $check_field('add','gender'))">
						<el-option v-for="o in list_gender" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','gender')">{{form['gender']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','age') || $check_field('add','age') || $check_field('set','age')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="年龄" prop="age">
					<el-input id="age" v-model="form['age']" placeholder="请输入年龄"
							  v-if="user_group === '管理员' || (form['physical_examination_plan_id'] && $check_field('set','age')) || (!form['physical_examination_plan_id'] && $check_field('add','age'))" :disabled="disabledObj['age_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','age')">{{form['age']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','package_name') || $check_field('add','package_name') || $check_field('set','package_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="套餐名称" prop="package_name">
					<el-input id="package_name" v-model="form['package_name']" placeholder="请输入套餐名称"
							  v-if="user_group === '管理员' || (form['physical_examination_plan_id'] && $check_field('set','package_name')) || (!form['physical_examination_plan_id'] && $check_field('add','package_name'))" :disabled="disabledObj['package_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','package_name')">{{form['package_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','package_fee') || $check_field('add','package_fee') || $check_field('set','package_fee')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="套餐费用" prop="package_fee">
					<el-input id="package_fee" v-model="form['package_fee']" placeholder="请输入套餐费用"
							  v-if="user_group === '管理员' || (form['physical_examination_plan_id'] && $check_field('set','package_fee')) || (!form['physical_examination_plan_id'] && $check_field('add','package_fee'))" :disabled="disabledObj['package_fee_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','package_fee')">{{form['package_fee']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','appointment_date') || $check_field('add','appointment_date') || $check_field('set','appointment_date')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="预约日期" prop="appointment_date">
					<el-date-picker :disabled="disabledObj['appointment_date_isDisabled']" v-if="user_group === '管理员' || (form['physical_examination_plan_id'] && $check_field('set','appointment_date')) || (!form['physical_examination_plan_id'] && $check_field('add','appointment_date'))" id="appointment_date"
						v-model="form['appointment_date']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','appointment_date')">{{form['appointment_date']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','details_of_physical_examination_items') || $check_field('add','details_of_physical_examination_items') || $check_field('set','details_of_physical_examination_items')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="体检项目详情" prop="details_of_physical_examination_items">
					<el-input type="textarea" id="details_of_physical_examination_items" v-model="form['details_of_physical_examination_items']" placeholder="请输入体检项目详情"
						v-if="user_group === '管理员' || (form['physical_examination_plan_id'] && $check_field('set','details_of_physical_examination_items')) || (!form['physical_examination_plan_id'] && $check_field('add','details_of_physical_examination_items'))" :disabled="disabledObj['details_of_physical_examination_items_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','details_of_physical_examination_items')">{{form['details_of_physical_examination_items']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="审核状态" prop="examine_state">
					<el-select id="examine_state" v-model="form['examine_state']"
						v-if="user_group === '管理员' || (form['examine_state'] && $check_examine()) || (!form['examine_state'] && $check_examine())">
						<el-option key="未审核" label="未审核" value="未审核"></el-option>
						<el-option key="已通过" label="已通过" value="已通过"></el-option>
						<el-option key="未通过" label="未通过" value="未通过"></el-option>
					</el-select>
					<div v-else>{{form["examine_state"]}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="审核回复" prop="examine_reply">
					<el-input id="examine_reply" v-model="form['examine_reply']" placeholder="请输入审核回复"
						v-if="user_group === '管理员' || (form['examine_reply'] && $check_examine()) || (!form['examine_reply'] && $check_examine())"></el-input>
					<div v-else>{{form["examine_reply"]}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "physical_examination_plan_id",
				url_add: "~/api/physical_examination_plan/add?",
				url_set: "~/api/physical_examination_plan/set?",
				url_get_obj: "~/api/physical_examination_plan/get_obj?",
				url_upload: "~/api/physical_examination_plan/upload?",

				query: {
					"physical_examination_plan_id": 0,
				},

				form: {
					"physical_examination_personnel": 0, // 体检人员
					"full_name":'', // 姓名
					"gender":'', // 性别
					"age":'', // 年龄
					"package_name":'', // 套餐名称
					"package_fee":'', // 套餐费用
					"appointment_date":'', // 预约日期
					"details_of_physical_examination_items":'', // 体检项目详情
					"examine_state": "未审核",
					"examine_reply": "",
					"physical_examination_plan_id": 0, // ID

				},
				disabledObj:{
					"physical_examination_personnel_isDisabled": false,
					"full_name_isDisabled": false,
					"gender_isDisabled": false,
					"age_isDisabled": false,
					"package_name_isDisabled": false,
					"package_fee_isDisabled": false,
					"appointment_date_isDisabled": false,
					"details_of_physical_examination_items_isDisabled": false,
				},
				// 用户列表
				list_user_physical_examination_personnel: [],
				// 用户组
				group_user_physical_examination_personnel: "",
				//性别选项列表
				list_gender: ['男','女'],
			}
		},
		methods: {
			/**
			 * 获取体检人员用户列表
			 */
			async get_list_user_physical_examination_personnel() {
                // if(this.user_group !== "管理员" && this.form["physical_examination_personnel"] === 0) {
                //     this.form["physical_examination_personnel"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=体检人员");
                if(json.result && json.result.list){
                    this.list_user_physical_examination_personnel = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			/**
			 * 获取体检人员用户组
			 */
			async get_group_user_physical_examination_personnel() {
				this.form["physical_examination_personnel"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=体检人员");
				if(json.result && json.result.obj){
					this.group_user_physical_examination_personnel = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_physical_examination_personnel(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_physical_examination_personnel.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
						for (let key in _this.form) {
							arrForm.push(key)
						}
						for (var i=0;i<arr.length;i++){
							for (var j=0;j<arrForm.length;j++){
								if (arr[i]===arrForm[j]){
									if (arr[i]!=="physical_examination_personnel") {
										_this.form[arrForm[j]] = res.result.obj[arr[i]]
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
										break;
									}else {
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
									}
								}
							}
						}
					}
				});
			},
			get_user_physical_examination_personnel(id){
				var obj = this.list_user_physical_examination_personnel.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				// 获取缓存数据附加
				form = $.db.get("form");
				$.push(this.form ,form);
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "体检人员":
							if(param["physical_examination_personnel"] > 0){
								param["physical_examination_personnel"] = this.user.user_id;
							}
							break;
					}
				}
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
        if (this.form["appointment_date"].indexOf("-")===-1){
          this.form["appointment_date"] = this.$toTime(parseInt(this.form["appointment_date"]),"yyyy-MM-dd")
        }
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				if(parseInt(this.form["appointment_date"]) > 9999){
					this.form["appointment_date"] = this.$toTime(parseInt(this.form["appointment_date"]),"yyyy-MM-dd")
				}
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/physical_examination_plan/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/physical_examination_plan/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/physical_examination_plan/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/physical_examination_plan/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/physical_examination_plan/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
			this.get_list_user_physical_examination_personnel();
			this.get_group_user_physical_examination_personnel();
		},
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>

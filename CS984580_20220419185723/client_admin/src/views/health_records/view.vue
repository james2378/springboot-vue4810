<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','physical_examination_personnel') || $check_field('add','physical_examination_personnel') || $check_field('set','physical_examination_personnel')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="体检人员" prop="physical_examination_personnel">
						<el-select v-if="user_group === '管理员' || (form['health_records_id'] && $check_field('set','physical_examination_personnel')) || (!form['health_records_id'] && $check_field('add','physical_examination_personnel'))" id="physical_examination_personnel" v-model="form['physical_examination_personnel']" :disabled="disabledObj['physical_examination_personnel_isDisabled']">
							<el-option v-for="o in list_user_physical_examination_personnel" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','physical_examination_personnel')" id="physical_examination_personnel" v-model="form['physical_examination_personnel']" :disabled="true">
							<el-option v-for="o in list_user_physical_examination_personnel" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','full_name') || $check_field('add','full_name') || $check_field('set','full_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="姓名" prop="full_name">
					<el-input id="full_name" v-model="form['full_name']" placeholder="请输入姓名"
							  v-if="user_group === '管理员' || (form['health_records_id'] && $check_field('set','full_name')) || (!form['health_records_id'] && $check_field('add','full_name'))" :disabled="disabledObj['full_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','full_name')">{{form['full_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','gender') || $check_field('add','gender') || $check_field('set','gender')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="性别" prop="gender">
					<el-select id="gender" v-model="form['gender']"
						v-if="user_group === '管理员' || (form['health_records_id'] && $check_field('set','gender')) || (!form['health_records_id'] && $check_field('add','gender'))">
						<el-option v-for="o in list_gender" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','gender')">{{form['gender']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','age') || $check_field('add','age') || $check_field('set','age')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="年龄" prop="age">
					<el-input id="age" v-model="form['age']" placeholder="请输入年龄"
							  v-if="user_group === '管理员' || (form['health_records_id'] && $check_field('set','age')) || (!form['health_records_id'] && $check_field('add','age'))" :disabled="disabledObj['age_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','age')">{{form['age']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','package_name') || $check_field('add','package_name') || $check_field('set','package_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="套餐名称" prop="package_name">
					<el-input id="package_name" v-model="form['package_name']" placeholder="请输入套餐名称"
							  v-if="user_group === '管理员' || (form['health_records_id'] && $check_field('set','package_name')) || (!form['health_records_id'] && $check_field('add','package_name'))" :disabled="disabledObj['package_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','package_name')">{{form['package_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','date_of_physical_examination') || $check_field('add','date_of_physical_examination') || $check_field('set','date_of_physical_examination')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="体检日期" prop="date_of_physical_examination">
					<el-date-picker :disabled="disabledObj['date_of_physical_examination_isDisabled']" v-if="user_group === '管理员' || (form['health_records_id'] && $check_field('set','date_of_physical_examination')) || (!form['health_records_id'] && $check_field('add','date_of_physical_examination'))" id="date_of_physical_examination"
						v-model="form['date_of_physical_examination']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','date_of_physical_examination')">{{form['date_of_physical_examination']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','doctor') || $check_field('add','doctor') || $check_field('set','doctor')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="医生" prop="doctor">
					<el-input id="doctor" v-model="form['doctor']" placeholder="请输入医生"
							  v-if="user_group === '管理员' || (form['health_records_id'] && $check_field('set','doctor')) || (!form['health_records_id'] && $check_field('add','doctor'))" :disabled="disabledObj['doctor_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','doctor')">{{form['doctor']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','details_of_physical_examination_items') || $check_field('add','details_of_physical_examination_items') || $check_field('set','details_of_physical_examination_items')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="体检项目详情" prop="details_of_physical_examination_items">
					<el-input type="textarea" id="details_of_physical_examination_items" v-model="form['details_of_physical_examination_items']" placeholder="请输入体检项目详情"
						v-if="user_group === '管理员' || (form['health_records_id'] && $check_field('set','details_of_physical_examination_items')) || (!form['health_records_id'] && $check_field('add','details_of_physical_examination_items'))" :disabled="disabledObj['details_of_physical_examination_items_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','details_of_physical_examination_items')">{{form['details_of_physical_examination_items']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','health_records') || $check_field('add','health_records') || $check_field('set','health_records')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="健康档案" prop="health_records">
					<el-input type="textarea" id="health_records" v-model="form['health_records']" placeholder="请输入健康档案"
						v-if="user_group === '管理员' || (form['health_records_id'] && $check_field('set','health_records')) || (!form['health_records_id'] && $check_field('add','health_records'))" :disabled="disabledObj['health_records_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','health_records')">{{form['health_records']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','health_guidelines') || $check_field('add','health_guidelines') || $check_field('set','health_guidelines')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="健康指南" prop="health_guidelines">
					<el-input type="textarea" id="health_guidelines" v-model="form['health_guidelines']" placeholder="请输入健康指南"
						v-if="user_group === '管理员' || (form['health_records_id'] && $check_field('set','health_guidelines')) || (!form['health_records_id'] && $check_field('add','health_guidelines'))" :disabled="disabledObj['health_guidelines_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','health_guidelines')">{{form['health_guidelines']}}</div>
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
				field: "health_records_id",
				url_add: "~/api/health_records/add?",
				url_set: "~/api/health_records/set?",
				url_get_obj: "~/api/health_records/get_obj?",
				url_upload: "~/api/health_records/upload?",

				query: {
					"health_records_id": 0,
				},

				form: {
					"physical_examination_personnel": 0, // 体检人员
					"full_name":'', // 姓名
					"gender":'', // 性别
					"age":'', // 年龄
					"package_name":'', // 套餐名称
					"date_of_physical_examination":'', // 体检日期
					"doctor":'', // 医生
					"details_of_physical_examination_items":'', // 体检项目详情
					"health_records":'', // 健康档案
					"health_guidelines":'', // 健康指南
					"health_records_id": 0, // ID

				},
				disabledObj:{
					"physical_examination_personnel_isDisabled": false,
					"full_name_isDisabled": false,
					"gender_isDisabled": false,
					"age_isDisabled": false,
					"package_name_isDisabled": false,
					"date_of_physical_examination_isDisabled": false,
					"doctor_isDisabled": false,
					"details_of_physical_examination_items_isDisabled": false,
					"health_records_isDisabled": false,
					"health_guidelines_isDisabled": false,
				},
				// 用户列表
				list_user_physical_examination_personnel: [],
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
        if (this.form["date_of_physical_examination"].indexOf("-")===-1){
          this.form["date_of_physical_examination"] = this.$toTime(parseInt(this.form["date_of_physical_examination"]),"yyyy-MM-dd")
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
				if(parseInt(this.form["date_of_physical_examination"]) > 9999){
					this.form["date_of_physical_examination"] = this.$toTime(parseInt(this.form["date_of_physical_examination"]),"yyyy-MM-dd")
				}
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/health_records/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/health_records/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/health_records/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/health_records/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/health_records/view','get');
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

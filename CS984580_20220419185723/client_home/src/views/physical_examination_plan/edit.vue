<template>
	<div class="diy_edit page_physical_examination_plan" id="physical_examination_plan_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row diy_edit_content_box'>
					<div v-if="$check_field('set','physical_examination_personnel') || $check_field('add','physical_examination_personnel') || $check_field('get','physical_examination_personnel')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>体检人员:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_physical_examination_personnel" :disabled="disabledObj['physical_examination_personnel_isDisabled']" v-model="form['physical_examination_personnel']" v-if="(form['physical_examination_personnel'] && $check_field('set','physical_examination_personnel')) || (!form['physical_examination_personnel'] && $check_field('add','physical_examination_personnel'))" >
								<option v-for="o in list_user_physical_examination_personnel" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','physical_examination_personnel')">{{ form['physical_examination_personnel'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','full_name') || $check_field('add','full_name') || $check_field('get','full_name')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>姓名:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_full_name" v-model="form['full_name']" placeholder="请输入姓名" v-if="(form['full_name'] && $check_field('set','full_name')) || (!form['full_name'] && $check_field('add','full_name'))"  :disabled="disabledObj['full_name_isDisabled']"/>
							<span v-else-if="$check_field('get','full_name')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','gender') || $check_field('add','gender') || $check_field('get','gender')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>性别:
							</span>
						</div>
						<!-- 选项 -->
						<div class="diy_field diy_down">
							<select id="form_gender" v-model="form['gender']" v-if="(form['gender'] && $check_field('set','gender')) || (!form['gender'] && $check_field('add','gender'))" >
								<option v-for="o in list_gender" :value="o">
									{{o}}
								</option>
							</select>
							<span v-else-if="$check_field('get','gender')">{{ form['gender'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','age') || $check_field('add','age') || $check_field('get','age')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>年龄:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_age" v-model="form['age']" placeholder="请输入年龄" v-if="(form['age'] && $check_field('set','age')) || (!form['age'] && $check_field('add','age'))"  :disabled="disabledObj['age_isDisabled']"/>
							<span v-else-if="$check_field('get','age')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','package_name') || $check_field('add','package_name') || $check_field('get','package_name')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>套餐名称:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_package_name" v-model="form['package_name']" placeholder="请输入套餐名称" v-if="(form['package_name'] && $check_field('set','package_name')) || (!form['package_name'] && $check_field('add','package_name'))"  :disabled="disabledObj['package_name_isDisabled']"/>
							<span v-else-if="$check_field('get','package_name')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','package_fee') || $check_field('add','package_fee') || $check_field('get','package_fee')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>套餐费用:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_package_fee" v-model="form['package_fee']" placeholder="请输入套餐费用" v-if="(form['package_fee'] && $check_field('set','package_fee')) || (!form['package_fee'] && $check_field('add','package_fee'))"  :disabled="disabledObj['package_fee_isDisabled']"/>
							<span v-else-if="$check_field('get','package_fee')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','appointment_date') || $check_field('add','appointment_date') || $check_field('get','appointment_date')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>预约日期:
							</span>
						</div>
						<!-- 日期 -->
						<div class="diy_field diy_date">
							<input type="date" :disabled="disabledObj['appointment_date_isDisabled']" id="form_appointment_date" v-model="form['appointment_date']" placeholder="请输入预约日期" v-if="(form['appointment_date'] && $check_field('set','appointment_date')) || (!form['appointment_date'] && $check_field('add','appointment_date'))" />
							<span v-else-if="$check_field('get','appointment_date')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','details_of_physical_examination_items') || $check_field('add','details_of_physical_examination_items') || $check_field('get','details_of_physical_examination_items')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>体检项目详情:
							</span>
						</div>
						<!-- 多文本 -->
						<div class="diy_field diy_desc">
							<textarea id="form_details_of_physical_examination_items" v-model="form['details_of_physical_examination_items']" v-if="(form['details_of_physical_examination_items'] && $check_field('set','details_of_physical_examination_items')) || (!form['details_of_physical_examination_items'] && $check_field('add','details_of_physical_examination_items'))" :disabled="disabledObj['details_of_physical_examination_items_isDisabled']" />
							<span v-else-if="$check_field('get','details_of_physical_examination_items')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="user_group === '管理员' || $check_examine()" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								审核状态:
							</span>
						</div>
						<div class="diy_field diy_select" v-if="$check_action('/physical_examination_plan/edit','examine')">
							<!--<span> {{ form['examine_state'] }} </span>-->
							<select v-model="form['examine_state']">
								<option value="未审核">
									未审核
								</option>
								<option value="已通过">
									已通过
								</option>
								<option value="未通过">
									未通过
								</option>
							</select>
						</div>
						<div class="diy_field diy_text" v-else>
							<span>
								{{ form['examine_state'] }}
							</span>
						</div>
					</div>
					<div v-if="user_group === '管理员' || $check_examine()" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								审核回复:
							</span>
						</div>
						<div class="diy_field diy_desc" v-if="$check_action('/physical_examination_plan/edit','examine')">
							<textarea v-model="form['examine_reply']"></textarea>
						</div>
						<div class="diy_field diy_text" v-else>
							<span>
								{{ form['examine_reply'] }}
							</span>
						</div>
					</div>
				</div>
				<div class="diy_edit_submit_box row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/physical_examination_plan/get_obj?",
				url_add: "~/api/physical_examination_plan/add?",
				url_set: "~/api/physical_examination_plan/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
					"physical_examination_personnel": 0,
					"full_name": "",
					"gender": "",
					"age": "",
					"package_name": "",
					"package_fee": "",
					"appointment_date": "",
					"details_of_physical_examination_items": "",
					"physical_examination_plan_id": 0,
				},

				obj: {
					"physical_examination_personnel": 0, // 体检人员
					"full_name":'', // 姓名
					"gender":'', // 性别
					"age":'', // 年龄
					"package_name":'', // 套餐名称
					"package_fee":'', // 套餐费用
					"appointment_date": new Date().getTime(),
					"details_of_physical_examination_items":'', // 体检项目详情
					"examine_state": "未审核",
					"examine_reply": "",
					"physical_examination_plan_id": 0,
				},

				// 表单字段
				form: {
					"physical_examination_personnel": 0, // 体检人员
					"full_name":'', // 姓名
					"gender":'', // 性别
					"age":'', // 年龄
					"package_name":'', // 套餐名称
					"package_fee":'', // 套餐费用
					"appointment_date": new Date().getTime(),
					"details_of_physical_examination_items":'', // 体检项目详情
					"examine_state": "未审核",
					"examine_reply": "",
					"physical_examination_plan_id": 0,
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
				//性别选项列表
				list_gender: ['男','女'],

				// ID字段
				field: "physical_examination_plan_id",
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
			async get_user_session_physical_examination_personnel(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=体检人员");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["physical_examination_personnel"] = user_id
								_this.disabledObj['physical_examination_personnel' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
                  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
                    for (var j = 0; j < arrForm.length; j++) {
                      if (arr[i] === arrForm[j]) {
                        if (arr[i] !== "physical_examination_personnel") {
                          _this.form[arrForm[j]] = res.result.obj[arr[i]]
                          _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                          break;
                        }
                      }
                    }
                  }
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
			},

			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/physical_examination_plan/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				if (form) {
					this.obj = $.push(this.obj ,form);
					this.form = $.push(this.form ,form);
				}
				var arr = []
				for (let key in form) {
					arr.push(key)
				}
				for (var i=0;i<arr.length;i++){
					this.disabledObj[arr[i] + '_isDisabled'] = true
				}
        if (JSON.stringify(this.form["appointment_date"]).indexOf("-")===-1) {
          this.form["appointment_date"] = this.$toTime(parseInt(this.form["appointment_date"]), "yyyy-MM-dd")
        }
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				var form = $.db.get("form");
				var obj = Object.assign({} ,form ,this.obj);
				if (obj) {
          delete(obj.examine_state)
          delete(obj.examine_reply)
					this.obj = $.push(this.obj ,obj);
				}
				if (form) {
          delete(form.examine_state)
          delete(form.examine_reply)
					this.form = $.push(this.form ,form);
				}
				if(func){
					func(json);
				}
			},

		},
		created() {
			this.get_user_session_physical_examination_personnel();
			this.get_list_user_physical_examination_personnel();
		},
	}
</script>

<style>
</style>

<template>
	<div class="diy_details page_physical_examination_package" id="physical_examination_package_details">
		<div class='warp'>
			<div class='container'>
				<div class='row'>
					<div class='col'>
						<div class='card_physical_examination_package'>
							<div_physical_examination_package :obj="obj"></div_physical_examination_package>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import div_physical_examination_package from "@/components/diy/div_physical_examination_package.vue";
	import mixin from "@/mixins/page.js";
	export default {
		mixins:[mixin],
		components:{
			div_physical_examination_package
		},
		data(){
			return{
				url_get_obj: "~/api/physical_examination_package/get_obj?",

				field: "physical_examination_package_id",

				obj: {
				},

				query: {
					"package_name": "",
					"physical_examination_taboo": "",
					"matters_needing_attention": "",
					"package_fee": "",
					"package_cover": "",
					"details_of_physical_examination_items": "",
					"physical_examination_package_id":0,
				},
				// 点赞
				praise: 0,
				// 点赞状态
				state_praise: false,
				
			}
		},
		methods:{
			get_obj_after(json) {
				// 判断是否获取到数据
				if (json && json.result && json.result.obj) {
					console.log(json.result.obj)
					var obj = json.result.obj;
					this.obj = obj
					// 增加点击数
					// 初始化收藏状态
					this.get_collect(obj);
				}
			},
			/**
			 * 初始化收藏状态
			 * @param {Object} obj
			 */
			get_collect(obj) {
				var user_id = this.user.user_id;

				var query = {
					source_table: "physical_examination_package",
					source_field: "physical_examination_package_id",
					source_id: obj["physical_examination_package_id"],
					user_id
				};

				this.$get('~/api/collect/count?', query, (res) => {
					console.log(res);
					if (res.result || res.result === 0) {
						var bl = res.result ? true : false;
						this.state_collect = bl;
						console.log("收藏状态：" + bl);
					}
					else if(res.error) {
						console.error(res.error);
					}
				});
			},
		 /****
			 * 分享点击
			 * 
			 *  */ 
			toShare(e){
				window.open(e);
			},
			/**
			 * 改变收藏状态
			 */
			change_collect() {
				var user_id = this.user.user_id;

				var query = {
					source_table: "physical_examination_package",
					source_field: "physical_examination_package_id",
					source_id: this.obj["physical_examination_package_id"],
					user_id
				};

				// 收藏状态
				if (this.state_collect) {
					this.state_collect = false;
					this.$get('~/api/collect/del?', query, (res) => {
						if(res.result){
							this.$toast("取消收藏");
						}
						else if (res.error){
							this.$toast(res.error.message);
							console.error(res.error);
						}
					});
				} else {
					this.state_collect = true;
					this.$post('~/api/collect/add?', query, (res) => {
						if (res.result) {
							this.$toast("收藏成功");
						}
						else if (res.error) {
							this.$toast(res.error.message);
							console.error(res.error);
						}
					});
				};
			},
		},
		
		mounted() {
			
		},
		
	}
</script>

<style>
.qecode {
  display: flex;
  align-content: center;
  width: 270px;
  background: white;
  position: absolute;
  left: 20%;
  top: 20px;
  z-index: 999;
}
.qecodeImg {
  display: flex;
  flex-direction: column;
  align-content: center;
  text-align: center;
  padding-top: 10px;
  margin: 0 auto;
}
.btn_share{
	display: inline-block;
	position: relative;
	top: 15px;
	background: rgb(240, 238, 238);
	border: 1px solid #ccc;
	height: 38px;
	margin-left: 10px;
	padding: 3px 10px;
	cursor: pointer;
	overflow: hidden;
	border-radius: 5px;
}
.btn_share_icon{
	width: 30px;
	height: 28px;
	float: left;
	margin-right: 10px;
}
.share_box{
	display: none;
	float: left;
}
.share_box img{
	width: 30px;
	height: 28px;
	margin-right: 10px;
}
.btn_share:hover .share_box{
	display: block;
}
</style>

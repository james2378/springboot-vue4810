<template>
	<div class="diy_home diy_list diy_physical_examination_package" id="diy_physical_examination_package_list">
		<!-- 列表 -->
		<div class="diy_view_list list list-x" v-if="show">
			<router-link class="diy_card goods diy_list_box_wrap" v-for="(o, i) in list" :key="i"
				:to="'/physical_examination_package/details?physical_examination_package_id=' + o['physical_examination_package_id']">
				<!-- 图片 -->
				<div class="diy_list_img_box" v-if="imgList.length" >
					<div class="diy_row" v-for="(item,index) in imgList" :key="item+index" v-show="$check_field('get',item.name,'/physical_examination_package/details') && +item.is_img_list">
						<div class="diy_title diy_list_img_title">
							<span>{{item.title}}:</span>
						</div>
						<div class="diy_field diy_img">
							<img :src="$fullUrl(o[item.name])" style="width:100%;height:100%" />
						</div>
					</div>
				</div>
				<!-- 内容 -->
				<div class="diy_list_item_box">
					<div class="diy_list_item_content" v-for="(item,index) in showItemList" :key="item+index">
						<div class="diy_row" :class="{[item.name]:true}" v-if="$check_field('get',item.name,'/physical_examination_package/details') && +item.is_img_list">
							<div class="diy_title">
								<span>{{item.title}}:</span>
							</div>
							<div class="diy_field diy_text">
								<span v-if="item.type == 'UID'" v-text="get_user_name(o[item.name])"></span>
								<span v-else-if="item.type == '日期'" v-text="$toTime(o[item.name],'yyyy-MM-dd')"></span>
								<span v-else-if="item.type == '时间'" v-text="$toTime(o[item.name],'hh:mm:ss')"></span>
								<span v-else-if="item.type == '日长'" v-text="$toTime(o[item.name],'yyyy-MM-dd hh:mm:ss')"></span>
								<span v-else v-text="o[item.name]"></span>
							</div>
						</div>
					</div>
				</div>
			</router-link>
		</div>
		<!-- 表格 -->
		<div class="diy_view_table" v-else>
			<table class="diy_table">
				<tr class="diy_row">
					<th class="diy_title" v-if="$check_field('get','package_name')">套餐名称
					</th>
					<th class="diy_title" v-if="$check_field('get','physical_examination_taboo')">体检禁忌
					</th>
					<th class="diy_title" v-if="$check_field('get','matters_needing_attention')">注意事项
					</th>
					<th class="diy_title" v-if="$check_field('get','package_fee')">套餐费用
					</th>
					<th class="diy_title" v-if="$check_field('get','package_cover')">套餐封面
					</th>
					<th class="diy_title" v-if="$check_field('get','details_of_physical_examination_items')">体检项目详情
					</th>
				</tr>
				<tr class="diy_row" v-for="(o,i) in list" :key="o+i">
					<td class="diy_field diy_text" v-if="$check_field('get','package_name')">
						<span>
							{{ o["package_name"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','physical_examination_taboo')">
						<span>
							{{ o["physical_examination_taboo"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','matters_needing_attention')">
						<span>
							{{ o["matters_needing_attention"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','package_fee')">
						<span>
							{{ o["package_fee"] }}
						</span>
					</td>
					<td class="diy_field" v-if="$check_field('get','package_cover')">
						<img class="diy_img" :src="o['package_cover']" />
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','details_of_physical_examination_items')">
						<span>
							{{ o["details_of_physical_examination_items"] }}
						</span>
					</td>
				</tr>
			</table>
		</div>
	</div>
</template>

<script>
	export default {
		props: {
			list: {
				type: Array,
				default: function() {
					return [];
				},
			},
			show: {
				type: Boolean,
				default: function(){
					return true;
				}
			}
		},
		data() {
			return {
						imgList: [
						{
							title: "套餐封面",
							name: "package_cover",
							type: "图片",
							is_img_list: "1"
						},
						],
						itemList: [
								{
									title: "套餐名称",
									name: "package_name",
									type: "文本",
									is_img_list: "1"
								},
								{
									title: "体检禁忌",
									name: "physical_examination_taboo",
									type: "文本",
									is_img_list: "1"
								},
								{
									title: "注意事项",
									name: "matters_needing_attention",
									type: "文本",
									is_img_list: "1"
								},
								{
									title: "套餐费用",
									name: "package_fee",
									type: "文本",
									is_img_list: "1"
								},
						],
						richList: [
								{
									title: "体检项目详情",
									name: "details_of_physical_examination_items",
									type: "多文本"
								},
						],
			};
		},
		methods: {
		},
		created() {
		},
		computed:{
			showItemList(){
				let arr = [];
				let _type = ["视频","音频","文件"];
				this.itemList.forEach(item => {
					if(_type.indexOf(item.type) === -1 && !!+item.is_img_list){
						arr.push(item)
					}
				})
				return arr.slice(0,4);
			}
		}
	};
</script>

<style scoped>
	.media {
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		flex-basis: 75%;
		min-height: 10rem;
	}

	.goods {
		display: flex;
		width: calc(25% - 1rem);
		margin: 0.5rem;
		padding: 0.5rem;
		flex-direction: column;
		justify-content: space-between;
		background-color: white;
		border-radius: 0.5rem;
	}

	.goods:hover {
		border: 0.2rem solid #909399;
		box-shadow: 0 0.1rem 0.3rem rgba(0, 0, 0, 0.15);
	}

	.goods:hover img {
		filter: blur(1px);
	}

	.price {
		font-size: 1rem;
		margin-right: 3px;
	}

	.price_ago {
		text-decoration: line-through;
		font-size: 0.5rem;
		color: #999;

	}

	.title {
		word-break: break-all;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		font-weight: 700;
		padding: .25rem;
	}

	.icon_cart {
		color: #FF5722;
		float: right;
	}

	@media (max-width: 992px) {

		.goods {
			width: calc(33% - 1rem);
			;
		}

	}

	@media (max-width: 768px) {

		.goods {
			width: calc(50% - 1rem);
			;
		}

	}
</style>


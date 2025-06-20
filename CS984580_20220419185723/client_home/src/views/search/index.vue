<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="体检资讯"
				source_table="article"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/physical_examination_personnel/list', 'get')"
				:list="result_physical_examination_personnel_full_name"
				title="体检人员姓名"
				source_table="physical_examination_personnel"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/physical_examination_package/list', 'get')"
				:list="result_physical_examination_package_package_name"
				title="体检套餐套餐名称"
				source_table="physical_examination_package"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/physical_examination_plan/list', 'get')"
				:list="result_physical_examination_plan_full_name"
				title="体检计划姓名"
				source_table="physical_examination_plan"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/physical_examination_plan/list', 'get')"
				:list="result_physical_examination_plan_package_name"
				title="体检计划套餐名称"
				source_table="physical_examination_plan"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/physical_examination_plan/list', 'get')"
				:list="result_physical_examination_plan_appointment_date"
				title="体检计划预约日期"
				source_table="physical_examination_plan"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/health_records/list', 'get')"
				:list="result_health_records_full_name"
				title="健康档案姓名"
				source_table="health_records"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/health_records/list', 'get')"
				:list="result_health_records_package_name"
				title="健康档案套餐名称"
				source_table="health_records"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
			"result_physical_examination_personnel_full_name":[],
			"result_physical_examination_package_package_name":[],
			"result_physical_examination_plan_full_name":[],
			"result_physical_examination_plan_package_name":[],
			"result_physical_examination_plan_appointment_date":[],
			"result_health_records_full_name":[],
			"result_health_records_package_name":[],
	};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取full_name
	 */
	get_physical_examination_personnel_full_name(){
		this.$get("~/api/physical_examination_personnel/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_physical_examination_personnel_full_name = json.result.list;
			result_physical_examination_personnel_full_name.map(o => o.title = o['full_name'])
	  			this.result_physical_examination_personnel_full_name = result_physical_examination_personnel_full_name
		 	}
		});
	},
	/**
	 * 获取package_name
	 */
	get_physical_examination_package_package_name(){
		this.$get("~/api/physical_examination_package/get_list?like=0", { page: 1, size: 10, "package_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_physical_examination_package_package_name = json.result.list;
			result_physical_examination_package_package_name.map(o => o.title = o['package_name'])
	  			this.result_physical_examination_package_package_name = result_physical_examination_package_package_name
		 	}
		});
	},
	/**
	 * 获取full_name
	 */
	get_physical_examination_plan_full_name(){
		this.$get("~/api/physical_examination_plan/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_physical_examination_plan_full_name = json.result.list;
			result_physical_examination_plan_full_name.map(o => o.title = o['full_name'])
	  			this.result_physical_examination_plan_full_name = result_physical_examination_plan_full_name
		 	}
		});
	},
	/**
	 * 获取package_name
	 */
	get_physical_examination_plan_package_name(){
		this.$get("~/api/physical_examination_plan/get_list?like=0", { page: 1, size: 10, "package_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_physical_examination_plan_package_name = json.result.list;
			result_physical_examination_plan_package_name.map(o => o.title = o['package_name'])
	  			this.result_physical_examination_plan_package_name = result_physical_examination_plan_package_name
		 	}
		});
	},
	/**
	 * 获取appointment_date
	 */
	get_physical_examination_plan_appointment_date(){
		this.$get("~/api/physical_examination_plan/get_list?like=0", { page: 1, size: 10, "appointment_date": this.query.word }, (json) => {
		  if (json.result) {
			var result_physical_examination_plan_appointment_date = json.result.list;
			result_physical_examination_plan_appointment_date.map(o => o.title = o['appointment_date'])
	  			this.result_physical_examination_plan_appointment_date = result_physical_examination_plan_appointment_date
		 	}
		});
	},
	/**
	 * 获取full_name
	 */
	get_health_records_full_name(){
		this.$get("~/api/health_records/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_health_records_full_name = json.result.list;
			result_health_records_full_name.map(o => o.title = o['full_name'])
	  			this.result_health_records_full_name = result_health_records_full_name
		 	}
		});
	},
	/**
	 * 获取package_name
	 */
	get_health_records_package_name(){
		this.$get("~/api/health_records/get_list?like=0", { page: 1, size: 10, "package_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_health_records_package_name = json.result.list;
			result_health_records_package_name.map(o => o.title = o['package_name'])
	  			this.result_health_records_package_name = result_health_records_package_name
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
		this.get_physical_examination_personnel_full_name();
		this.get_physical_examination_package_package_name();
		this.get_physical_examination_plan_full_name();
		this.get_physical_examination_plan_package_name();
		this.get_physical_examination_plan_appointment_date();
		this.get_health_records_full_name();
		this.get_health_records_package_name();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_physical_examination_personnel_full_name();
	  this.get_physical_examination_package_package_name();
	  this.get_physical_examination_plan_full_name();
	  this.get_physical_examination_plan_package_name();
	  this.get_physical_examination_plan_appointment_date();
	  this.get_health_records_full_name();
	  this.get_health_records_package_name();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>

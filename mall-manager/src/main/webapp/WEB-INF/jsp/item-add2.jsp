<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style type="text/css">
.form-item {
	margin-bottom: 15px;
	width: 100%;
	float: left;
}

.form-item label {
	min-width: 72px;
	display: inline-block;
}

.form-item input, select {
	width: 170px;
}
</style>
<div class="super-theme-example">
	<form id="itemAddForm" method="post">
		<div class="form-item">
			<label for="" class="label-top">商品类目:</label> <a
				href="javascript:void(0)" class="easyui-linkbutton selectItemCat">选择类目</a>
			<input type="hidden" name="cid" style="width: 280px;"></input>
		</div>
		<div class="form-item">
			<label for="" class="label-top">商品标题:</label> <input
				class="easyui-textbox" type="text" name="title"
				data-options="required:true" style="width: 280px;"></input>
		</div>
		<div class="form-item">
			<label for="" class="label-top">商品卖点:</label> <input
				class="easyui-textbox" name="sellPoint"
				data-options="multiline:true,validType:'length[0,150]'"
				style="height: 60px; width: 280px;"></input>
		</div>
		<div class="form-item">
			<label for="" class="label-top">商品价格:</label> <input
				class="easyui-numberbox" type="text" name="price"
				data-options="min:0,max:99999999,required:true" />
		</div>
		<div class="form-item">
			<label for="" class="label-top">库存数量:</label> <input
				class="easyui-numberbox" type="text" name="num"
				data-options="min:1,max:99999999,precision:0,required:true" />
		</div>
		<div class="form-item">
			<label for="" class="label-top">条形码:</label> <input
				class="easyui-textbox" type="text" name="barcode"
				data-options="validType:'length[1,30]'" />
		</div>
		<div class="form-item">
			<label for="" class="label-top">商品图片:</label> <a
				href="javascript:void(0)" class="easyui-linkbutton picFileUpload">上传图片</a>
			<input type="hidden" name="image" />
		</div>
			<label for="" class="label-top">商品描述:</label> 
			<textarea id="editor_id" name="desc" style="width:700px;height:300px;">
			&lt;strong&gt;HTML内容&lt;/strong&gt;
</textarea>
		</div>
		
		<div class="form-item">
			<a href="javascript:void(0)" class="easyui-linkbutton warning"
				onclick="clearForm()">取消</a> <a href="javascript:void(0)"
				class="easyui-linkbutton success" onclick="submitForm()">提交</a>
		</div>
	</form>
</div>
<script type="text/javascript">
	var itemAddEditor;
	//页面初始化完毕后执行此方法
	$(function() {
		//创建富文本编辑器
		itemAddEditor = TT.createEditor("#itemAddForm [name=desc]");
		//初始化类目选择和图片上传器
		TT.init({
			fun : function(node) {
				//根据商品的分类id取商品 的规格模板，生成规格信息。第四天内容。
				TT.changeItemParam(node, "itemAddForm");
			}
		});
	});
	function submitForm() {

		if (!$('#itemAddForm').form('validate')) {
			$.messager.alert('提示', '表单还未填写完成!');
			return;
		}
		itemAddEditor.sync();
		$("#itemAddForm").serialize()
		alert($("#itemAddForm").serialize());
		$.post("/item/save", $("#itemAddForm").serialize(), function(data) {
			if (data.status == 200) {
				$("#dgTbItem").datagrid("reload");
				$.messager.alert('操作成功', '恭喜您添加产品成功', 'warning', function() {
					$('#item-list').click();
				});
			}
		});
	}
	function clearForm() {
		$('#itemAddForm').form('reset');
	}
</script>
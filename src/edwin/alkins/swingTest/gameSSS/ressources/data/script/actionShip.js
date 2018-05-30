var objAction = new Object();
objAction.action = function(core){
	print(core);
	var listV = core.getValue("list_vaiseaux");
	listV.get(0).setValue("size_stockage",9000);
	print(core);
}
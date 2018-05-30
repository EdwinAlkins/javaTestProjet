/**
 * 
 */
//importClass(edwin.alkins.swingTest.gameSSS.core.basicObj.BasicObjectCore);

function test() {
	var BasicObjectCore = Java.type("edwin.alkins.swingTest.gameSSS.core.basicObj.BasicObjectCore");
    return new BasicObjectCore;
}

function main() {
	var BasicObjectCore = Java.type("edwin.alkins.swingTest.gameSSS.core.basicObj.BasicObjectCore");
	var o = new BasicObjectCore;
    return "{o:10}";
}
var fs = require('fs');
var glob = require('glob');

glob("testcase*", {}, function(err, files) {
	files.forEach(function(filename) {
		var fileLines = fs.readFileSync(filename, 'ascii');
		// console.log(fileLines);
		fileLines = fileLines.split("\n");
		var regex = ".*"+fileLines[1];
		var out = "";
		for (var i = 2; i < fileLines.length; i++) {
			var line = fileLines[i];
			var res = line.match(regex);
			if (res) {
				out += line + "\tPASS\t" + res[0] + "\n";
			} else {
				out += line + "\tFAIL\n";
			}
		}
		fs.writeFileSync("solved" + filename, out);

	})
})
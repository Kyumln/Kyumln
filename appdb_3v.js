var fs = require('fs');
var ejs = require('ejs');
var mysql = require('mysql');
var express = require('express');
var app = express();
var port = 3000;

var bodyParser = require('body-parser');
var conn = mysql.createConnection({
	host: "localhost",
	user: "root",
	password: "mysql123",
	database: "testdb"
});

app.use(express.static(__dirname + '/public'));
app.use(bodyParser.urlencoded({extended : true}));

app.get('/', function (req, res) {
	res.sendFile(__dirname + '/public/main.html');
});

app.post('/w', function (req, res) {
	const command = req.body.command;
//	conn.connect(function(err){
//		if(err) throw err;
/*		else*/ if(command == "all"){
		fs.readFile(__dirname + '/public/list.html', 'utf8', function(error, data){
		if(error){
			console.log('readFile Error');
		}else{
			conn.query('select * from book', function(error, results){
				if(error){
					console.log('error : ', error.message);
				}else{
					res.send( ejs.render(data, {
						prodList : results
					}));
				}
			});
		}
	})
		}else if(command == "name"){
		fs.readFile(__dirname + '/public/name.html', 'utf8', function(error, data){
		if(error){
			console.log('readFile Error');
		}else{
			conn.query('select * from book', function(error, results){
				if(error){
					console.log('error : ', error.message);
				}else{
					res.send( ejs.render(data, {
						prodList : results
					}));
				}
			});
		}
	})
		}else if(command == "price"){
		fs.readFile(__dirname + '/public/price.html', 'utf8', function(error, data){
		if(error){
			console.log('readFile Error');
		}else{
			conn.query('select * from book', function(error, results){
				if(error){
					console.log('error : ', error.message);
				}else{
					res.send( ejs.render(data, {
						prodList : results
					}));
				}
			});
		}
	})
		}else if(command == "author"){
		fs.readFile(__dirname + '/public/no.html', 'utf8', function(error, data){
		if(error){
			console.log('readFile Error');
		}else{
			conn.query('select * from book', function(error, results){
				if(error){
					console.log('error : ', error.message);
				}else{
					res.send( ejs.render(data, {
						prodList : results
					}));
				}
			});
		}
	})
		}else if(command == "홍길동"){
		fs.readFile(__dirname + '/public/hong.html', 'utf8', function(error, data){
		if(error){
			console.log('readFile Error');
		}else{
			conn.query(`select * from book where name ="홍길동"`, function(error, results){
				if(error){
					console.log('error : ', error.message);
				}else{
					res.send( ejs.render(data, {
						prodList : results
					}));
				}
			});
		}
	})
		}
//	});
});

app.listen(port, function(err) {
	if(err) throw err;
	console.log(`App listening on port ${port}`);
});

#!/bin/bash
#!/bin/ksh

for var in {2018..2022}
do
	for var1 in {1..12}
	do
		if [ "${var1}" -le 9 ]
		then 
			var1="0$var1"
		fi

		for var2 in {1..31}
		do
			if [ "${var2}" -le 9 ]
			then
				var2="0$var2"
			fi

			date="$var$var1$var2"
			hadoop jar WordCount2.jar WordCount2 /user/InputDATA/Seoul_Output$date.txt /user/OutputDATA/sort$date/ -skip /user/patterns.txt
		done	
	done

done

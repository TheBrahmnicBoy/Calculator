javac Calculator.java

if [ $? == 0 ]
then
  java Calculator
else
  printf "\n\nError - Exit code $?"
fi  
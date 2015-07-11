set path=%PATH%;%JAVA_HOME%/bin;
javac ./src/*.java
cd ./src
java words_tweeted ../tweet_input/tweets.txt ../tweet_output/ft1.txt
java median_unique ../tweet_input/tweets.txt ../tweet_output/ft2.txt
cd ..

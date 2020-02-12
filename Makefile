all: build processBuildDir run

.PHONY: build
build:
	./gradlew clean checkstyleMain jar

.PHONY: processBuildDir
processBuildDir:
	mv ./build/libs/formatter-1.0-RELEASE.jar . && \
	rm -rf ./build/* && \
	mv ./formatter-1.0-RELEASE.jar ./build/formatter.jar

.PHONY: run
run:
	java -jar ./build/formatter.jar ${IN} ${OUT}

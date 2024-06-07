# Diplom_3
# запуск в Chrome

mvn test 

# запуск с Yandex

bash
mvn  -Dbrowser=yandex -Ddriver.version=122.0.6261.128 -Dwebdriver.yandex.bin=/usr/bin/yandex-browser test

# Diplom_3
# запуск в Chrome

mvn test 

# запуск с Yandex

bash
mvn  -Dbrowser=yandex -Ddriver.version=122.0.6261.94 -Dwebdriver.yandex.bin=/usr/bin/yandex-browser test

в зависимости от версии яндекс браузера может потребоваться версия 122.0.6261.128

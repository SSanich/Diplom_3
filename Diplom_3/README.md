# Diplom_3
# запуск в Chrome

mvn test 

Не удалось подружить драйвер яндекс браузера с моей машиной, далее используется 
Firefox
# запуск с Firefox

mvn -Dbrowser=firefox test

Если mvn test падает с ошибкой 500, и не находит бинарник, то запускаем с параметром

bash
mvn -Dbrowser=firefox -Dwebdriver.firefox.bin=/usr/bin/firefox test

pipeline {
    agent any

    stages {

        stage('build') {
            steps {
              sh '''
                 cd ./cloud-game-store-customer-service
                 ./mvnw -DskipTests clean compile
		 cd ../cloud-game-store-admin
                 ./mvnw -DskipTests clean compile
		 cd ./cloud-game-store-eureka
                 ./mvnw -DskipTests clean compile
		 cd ./cloud-game-store-config
                 ./mvnw -DskipTests clean compile
		 cd ./cloud-game-store-inventory-service
                 ./mvnw -DskipTests clean compile
		 cd ./cloud-game-store-invoice-service
                 ./mvnw -DskipTests clean compile
		 cd ./cloud-game-store-level-up-service
                 ./mvnw -DskipTests clean compile
		 cd ./cloud-game-store-product-service
                 ./mvnw -DskipTests clean compile
		 cd ./cloud-game-store-retail
                 ./mvnw -DskipTests clean compile
              '''
            }
        }

        stage('test') {
            steps {
              sh '''
     	         cd ./cloud-game-store-customer-service
                 ./mvnw test
                 cd ../cloud-game-store-admin
                 ./mvnw test
                 cd ../cloud-game-store-eureka
                 ./mvnw test
                 cd ../cloud-game-store-config
                 ./mvnw test
                 cd ../cloud-game-store-inventory-service
                 ./mvnw test
                 cd ../cloud-game-store-invoice-service
                 ./mvnw test
                 cd ../cloud-game-store-level-up-service
                 ./mvnw test
                 cd ../cloud-game-store-product-service
                 ./mvnw test
                 cd ../cloud-game-store-retail
                 ./mvnw test
              '''
            }
        }

        stage('deliver') {
            steps {
              sh '''
                 

        	 cd ./cloud-game-store-customer-service
                 ./mvnw -DskipTests install
                 cd ../cloud-game-store-admin
                 ./mvnw -DskipTests install
                 cd ../cloud-game-store-eureka
                 ./mvnw -DskipTests install
                 cd ../cloud-game-store-config
                 ./mvnw -DskipTests install
                 cd ../cloud-game-store-inventory-service
                 ./mvnw -DskipTests install
                 cd ../cloud-game-store-invoice-service
                 ./mvnw -DskipTests install
                 cd ../cloud-game-store-level-up-service
                 ./mvnw -DskipTests install
                 cd ../cloud-game-store-product-service
                 ./mvnw -DskipTests install
                 cd ../cloud-game-store-retail
                 ./mvnw -DskipTests install
              '''
            }
        }

    }
}


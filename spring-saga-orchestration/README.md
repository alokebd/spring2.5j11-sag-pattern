# event-driven-microservices
#Event-driven-microservices by using axon

1. If it is not download, please download axonserver (https://download.axoniq.io/axonserver/AxonServer.zip)
2. Unzip anc change following in axonserver.properties
a). axoniq.axonserver.standalone=true
b). Uncommeted grpc port (axoniq.axonserver.port=8124)
3. Run the axonserver.jar (java -jar axonserver.jar or ./axonserver.jar)
4. Check UI (http://localhost:8024/)

5. Start all services (OrderService, PaymentService, ProductService, ShipmentService and UserService)

6. Test by placing order from Postman (http://localhost:9091/orders) by following order attributes and check the Axion Server and DB (H2 -http://localhost:9091/h2-cosole)
{
    "productId": "235429d-adf7-940-6ebc5878",
    "quantity": "2",
    "addressId": "17fa-toronca-259876",
    "userId": "01"
}



NOTE: SAGA is an event driven pattern where following vanilla of coding required to address this architecture.

1). The starting point of services required to declare the sage for annotation-based configuration (in this example, it starts with OrderService).

2). The @StartSaga and @EndSaga will register the @SagaEventHandler (check OrderProcessingSaga.java file)

3). The @Aggregate the life cycle of @CommandHandler and @EventSourcingHandler. As an example, product controller creates command that sends to 
CommandGateway. The gateway binds with @SagaEventHandler. Now the event will be published by Aggregare functionality.


/products (post) 
    ->Controller -(CreateProductCommand)
	->Command Gateway -(CreateProductCommand : handlers)
	->Aggregare - (publishers)-> Event Store (Axion DB)
	            - (ProductCreareEvent: listenes)-
				<- Event Handler <-Repository <-DB (h2)





more than one HSFConsumerBean of a class
```java
@HSFConsumer
@Qualifier("uicReadService")
@Autowired
private UicReadService uicReadService;
```
only one HSFConsumerBean object
```java
@HSFConsumer
@Autowired
private UicReadService uicReadService;
```

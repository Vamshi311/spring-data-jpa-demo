### Spring Data JPA Query Keywords:

* List<Speaker> findByFirstNameAndLastName(String firstName, String lastName); -- using and operator
* List<Speaker> findByFirstNameOrLastName(String firstName, String lastName); -- using or operator
* Speaker findFirstByFirstNameOrLastName(String firstName, String lastName); -- using First operator to return top element
* List<Speaker> findByFirstNameNot(String firstName); -- using != operator
* List<Speaker> findByFirstNameContains(String firstName); -- using like %?% operator
* List<Speaker> findByFirstNameLike(String firstName); -- using like operator ex: findByFirstNameLike("john%")
* List<Speaker> findByFirstNameNotLike(String firstName); -- using not like operator
* List<Speaker> findByFirstNameStartingWith(String firstName); -- using like operator ex: findByFirstNameLike("john"). % not needed.
* List<Speaker> findByFirstNameEndingWith(String firstName); -- using like operator ex: findByFirstNameLike("john"). % not needed.
* List<Speaker> findBySessionLengthGreaterThan(Integer length); -- using like operator ex: findBySessionLengthGreaterThan(30).
* List<Speaker> findBySessionLengthLessThan(Integer length); -- using like operator ex: findBySessionLengthLessThan(30).
* List<Speaker> findBySessionLengthGreaterThanEqual(Integer length); -- using like operator ex: findBySessionLengthGreaterThanEqual(30).
* List<Speaker> findBySessionLengthLessThanEqual(Integer length); -- using like operator ex: findBySessionLengthLessThanEqual(30).

### Date filters
* List<Speaker> findByStartDateBewteen(Date begin, Date end);
* List<Speaker> findByStartDateBefore(Date date);
* List<Speaker> findByStartDateAfter(Date date);

### Boolean filters
* List<Speaker> findByIncludesWorkshopTrue();
* List<Speaker> findByIncludesWorkshopFalse();

### Null or not null
* List<Speaker> findBySpeakerPhotoNull();
* List<Speaker> findBySpeakerPhotoIsNull();
* List<Speaker> findBySpeakerPhotoNotNull();
* List<Speaker> findBySpeakerPhotoIsNotNull();

### List filters:
* List<Speaker> findByCompanyIn(List<String> companies);
* List<Speaker> findByCompanyNotIn(List<String> companies);

### Ignore case:
* List<Speaker> findByFirstNameIgnoreCase(String firstName); -- using ignore case. will convert both parameter and column value to upper case before comparing.
* List<Speaker> findByFirstNameContainsIgnoreCase(String firstName); -- using ignore case. will convert both parameter and column value to upper case before comparing.

### Order By Clause:
* List<Speaker> findByFirstNameOrderByFirstNameAsc(String firstName);
* List<Speaker> findByFirstNameOrderByFirstNameDesc(String firstName);

### First, Top and Distinct:
* Speaker findFirstByStartDateBewteen(Date begin, Date end);
* List<Speaker> findTop5ByStartDateBewteen(Date begin, Date end);
* List<Speaker> findDistinctByStartDateBewteen(Date begin, Date end);

* Query DSL - writing abstract methods inside JpaRepository like "Speaker findFirstByStartDateBewteen(Date begin, Date end);".
* JPQL - writing SQL type queries using @Query annotation on top of abstract methods inside JpaRepository.


### Named Queries:

Using named queries instead of dynamic queries may improve code organization by separating the JPQL query strings from the Java code. Also a good important stuff is that some provider processed the JPQL inside the named queries at the start up time, this gives a hit on the performance, in the other case, persistence provider is not aware of the query existence and does not have the chance to process it on start up and need to run the process when it is required.

 ```
@Entity
@NamedQueries({
    @NamedQuery(name="Country.findAll", // name should be prefixed by Entity name
                query="SELECT c FROM Country c"),
    @NamedQuery(name="Country.findByName",
                query="SELECT c FROM Country c WHERE c.name like %:name"),
}) 
public class Country {
  ...
}
```
  
### Usage of Named queries:

  ```
interface CountryRepository extends JpaRepository<Country, Long> {
	List<Country> findByName(@Param("name") String name); //method name matches named query name.
}

or 

Query query = entityManager.createNamedQuery("Country.findAll");
List<Country> countries = query.getResultList();

Similarly, we have @NamedNativeQuery to support native queries.

@Entity
@NamedNativeQuery(name="Country.findByName", query="SELECT * FROM Country c WHERE c.name = :name", resultClass = Country.class)
public class Country {
  ...
}
```
  
### Usage of native query:
@Query(value = "SELECT * FROM country where name = :name", nativeQuery = true)
public List<Country> getCountries(@Param("name") Set<String> name);


### Paging and Sorting:

```
interface CountryRepository extends JpaRepository<Country, Long> {
	Page<Country> findByName(@Param("name") String name, Pageable pageable); // page will provide additional details like total number of pages, total elements etc
	List<Country> findByName(@Param("name") String name, Pageable pageable); // here it simply returns list elements.
}
```
  
Usage:
```
Pageable pageable = PageRequest.of(1, 5, Sort.by(Sort.Direction.DESC, "id")); // we are expecting 1st page of size 5 order by id desc.
Page<Country> page = countryRepository.findByName("john", pageable);
List<Country> page = countryRepository.findByName("john", pageable);

Custom Repositories:
If you want your JPA repository to implement some customic logic using services then we can go for custom repository.

// define the custom functionality as part of sepaarte interface
interface CountryCustomRepository {
	List<Country> getCustomCountries(String name);
}

// Our JPA repository should extend our custom repository interface
interface CountryRepository extends JpaRepository<Country, Long>, CountryCustomRepository {
	List<Country> findByName(@Param("name") String name); //method name matches named query name.
}

// implement custom functionality as part of class that has same name as custom interface with suffix "impl":
class CountryCustomRepositoryImpl implements CountryCustomRepository {
	List<Country> getCustomCountries(String name) {
	}
}
```
  
Now JPA will find this custom repository implementation class and extend it before implementing JPA repository interface methods.

### Auditing Support:
Spring Data JPA provides auditing support by providing below annotations:
* @CreatedBy - data type is String, Integer or any custom entity
* @CreatedDate - data type is DateTime
* @LastModifiedBy - data type is String, Integer or any custom entity
* @LastModifiedDate - data type is DateTime

It is easy for JPA to audit @CreatedDate and @LastModifiedDate. To audit the createdBy/LastModifiedBy user, JPA provides an interface AuditorAware<T>.
Example:
```  
public class SecurityAuditorAware implements AuditorAware<User> {
	public Optional<User> getCurrentAuditor() {
		.....
		// you can use spring's security context current user to figure out the current auditor.
		return user;
	}
}
```
To enable the auditing, we need to add @EnableJpaAuditing annotation to @Configuration class and define AuditorAware bean.
Example:
```
@Configuration
@EnableJpaAuditing
public class AuditingConfiguraion {

	public AuditorAware<User> auditorProvider() {
		return new SecurityAuditorAware();
	}
}
```
  
### Locking:

If a column is annotated with @Version annotation the persistent unit will increment that column value upon each commit. If the version number of entity is greater than version value that is being written now, then JPA will throw an error.
Otherwise it accepts it.
```
@Entity
public class CountryEntity {

	@Version
	private Long version;

}
```
  
We can also specify the @Lock annotation to specify the locking mode for a repository method.
Example:
```
@Lock(LockModeType.PESSIMISTIC_WRITE)
List<Model> findByAttributeName(String name);
```
  
### There are two types of locks.
* Optimistic - If version doesn't match, throws OptmimisticLockException
* Pessimistic Locking - Long term locks the data for the transaction duration, preventing others from accessing the data until the transaction commits.

### Reference:
* https://www.baeldung.com/jpa-optimistic-locking
  

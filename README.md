# CleanMVVMSample is sample project using Mvvm, Hilt, Retrofit, DataBinding, Coil, Diffutil.
- Clean architecture have 3 different categories
1. **Data Layer:** The outermost layer responsible for handling data retrieval and storage. It includes the implementation of repositories and data sources that interact with databases, APIs, or other external data providers.
2. **Domain Layer:** The middle layer that contains the business logic and use cases of the application. Here, the core business rules and operations are defined independently of any specific framework or technology.
3. **Presentation Layer:** The innermost layer responsible for handling the user interface and user interactions. It orchestrates the interaction between the user interface components, ViewModel (or Presenter), and the Domain layer.

## we only focus on UseCase and Repository creation and implementations
- First creating Repository interface that have one method for overriding in its implementation.
```kotlin
interface UseCaseRepository {
    suspend fun getPokemon() : NetworkResult<PokedexResponse>
}
```

- Implement UseCaseRepository in another class and provide that implemented class reference using hilt to usecaseimpl class and send response according to data we got.
```kotlin
class UseCaseRepositoryImpl @Inject constructor(private val apiService: APIService): UseCaseRepository {
    override suspend fun getPokemon(): NetworkResult<PokedexResponse> {
        val response = apiService.getPokemon()
        if (response.isSuccessful && response.body() != null) {
            return NetworkResult.Success(response.body()!!)
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            return NetworkResult.Error(errorObj.getString("message"))
        } else {
            return NetworkResult.Error("Something Went Wrong")
        }
    }
}
```

- Crating UseCase GetAllPokemonUseCase interface having one method.
```kotlin
interface GetAllPokemonUseCase {
    suspend operator fun invoke(): NetworkResult<PokedexResponse>
}
```

- Creating class GetAllPokemonUseCaseImpl that implement UseCase 
```kotlin
class GetAllPokemonUseCaseImpl @Inject constructor(
    private val useCaseRepository: UseCaseRepository
) : GetAllPokemonUseCase {
    override suspend operator fun invoke(): NetworkResult<PokedexResponse> {
        return useCaseRepository.getPokemon()
    }
}
```

- For providing above class's reference in our Viewmodel and UseCaseRepositoryImpl class to GetAllPokemonUseCaseImpl using Hilt Bind method
```kotlin
@InstallIn(SingletonComponent::class)
@Module
abstract class UseCaseModule {
    
    @Binds
    abstract fun getUseCase(getAllPokemonUseCaseImpl: GetAllPokemonUseCaseImpl): GetAllPokemonUseCase
    
    @Binds
    abstract fun provideMyRepository(useCaseRepositoryImpl: UseCaseRepositoryImpl): UseCaseRepository

}
```

- At last we get reference of GetAllPokemonUseCase in our ViewModel class and we use live data to notify Activity.
```kotlin
@HiltViewModel
class MainViewModel @Inject constructor(private val getAllPokemonUseCase: GetAllPokemonUseCase): ViewModel() {

    private val _pokLiveData = MutableLiveData<NetworkResult<PokedexResponse>>()
    val pokLiveData get() = _pokLiveData

    fun getAllPokemon() {
        viewModelScope.launch {
            pokLiveData.value = getAllPokemonUseCase()
        }
    }

}
```

### Build with lattest library till 16-05-2025
### This app is build in Kotlin.

<p align="center">
  <img src="images/image1.png" width="350" title="First screen">
    <img src="images/image2.png" width="350" alt="second screen">
</p>
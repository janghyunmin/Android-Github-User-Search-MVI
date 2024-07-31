# :wave: MVI 패턴 데모 앱 #토이 프로젝트#2

> ### MVI 패턴을 이용한 깃헙 사용자 검색 앱
  > 시연 영상
> 
<div style="display: flex; justify-content: space-between; width="100%;">
  <video src="https://github.com/user-attachments/assets/6d9da99b-86f5-4a2c-98ed-1cb7a975bdb2" style="width: 50%;"></video>
  <video src="https://github.com/user-attachments/assets/9179608e-0393-418a-b07a-fb83d5d955f3" style="width: 50%;"></video>
</div> 


<!-- 회원 조회 이미지 -->
<div style="display: flex; justify-content: space-between;">
    <img src="https://github.com/user-attachments/assets/264061ce-f3ed-414d-b106-c377f64a92ea" style="width: 30%; height: 30%;"/>
    <img src="https://github.com/user-attachments/assets/e3136bc5-0ce7-43d7-9038-ca7cdcaedaf7" style="width: 30%; height: 30%;"/>
</div>

<!-- 북마크 이미지 -->
<div style="display: flex; justify-content: space-between;">
    <img src="https://github.com/user-attachments/assets/ffdc3d54-33a0-4f79-ba1e-48c1ec1ee6ca" style="width: 70%; height: 100%;"/> <br>
    <img src="https://github.com/user-attachments/assets/a23c34d6-3f2e-4296-bac9-e02b36213bca" style="width: 70%; height: 100%;"/> <br>
    <img src="https://github.com/user-attachments/assets/08274702-c2a5-4476-9249-fd69e24d2f0f" style="width: 70%; height: 100%;"/> <br>
</div>

<br>


* Github 사용자 검색 기능
  * 검색한 사용자 리스트 출력
* 사용자 북마크 저장/해제 기능
* 딥링크 웹뷰 호출
* Room Database 로 로컬 데이터 저장

## 🔧 Stack
- **lib version** : kotlin 1.9.0 / ksp 2.0.10-RC-1.0.24 / dagger-hilt 2.51.1 / room  2.6.1
- **Language** : Kotlin , Compose, xml
- **Framework** : Android Studio
- **API** : [https://api.github.com](https://api.github.com)

## 시작하게 된 계기
> MVVM에서 해결하지 못하는 상태 문제와 부수 효과 문제가 확인되었고, 단점을 더 보완시킨 MVI 패턴에 대하여 연구 해보고 싶어 진행하게 되었습니다.

<h3>상태 문제</h3>
프로그레스 바의 상태, 버튼 활성화 상태와 같이 화면에 나타나는 모든 정보를 상태라고 하는데,
의도치 않은 방향으로 상태가 제어된다면 이것을 상태 문제라고 합니다.

<h3>부수 효과(side effect)</h3>
원래의 목적과 다른 효과 또는 부작용이 발생하는 상태를 의미
예를 들어 네트워크 통신 때 데이터를 가져오는 함수는 데이터를 못 가져올 수도 있다.
function add(a, b){a+b+c}는 a와 b를 더한 값의 예상과 다르게 c 때문에 값이 변경될 수도 있다.

- 자주 사용하던 MVVM 패턴에 대하여 단점을 찾다보니 단점을 보완한 MVI 패턴에 대하여 연구를 시작하게되었습니다.
- 단방향 데이터 구조와 View 의 명확한 상태관리를 사용해보고싶어 MVI 패턴에 관심을 가지게 되었습니다.

> MVVM vs MVI 패턴 비교

| Aspect             | MVVM                                     | MVI                                           |
|--------------------|------------------------------------------|-----------------------------------------------|
| **데이터 흐름**    | 양방향 (View ↔ ViewModel)                | 단방향 (Intent → ViewModel → View)                  |
| **상태 관리**      | LiveData 등으로 부분적 상태 관리          | 단일 상태 객체로 전체 상태 관리                             |
| **단순성**         | 상대적으로 덜 복잡함                     | 상대적으로 더 복잡함                                      |
| **테스트 용이성**  | ViewModel 테스트가 용이                  | 상태와 Intent 테스트가 용이                               |
| **보일러플레이트** | 데이터 바인딩과 ViewModel 설정 필요       | 상태와 Intent 정의 필요                                    |

<u>이러한 이유로 MVI 패턴을 적용한 데모앱을 만들게 되었습니다.</u>

## :grey_question: 고민한점
- MVI 기본 구조 설계는 어떻게 해야하는가? ( 가장 어렵게 느껴졌고 러닝커브가 상당한 느낌이였습니다. )
- Event : UiEvent, State : UiState, Effect : UiEffect 는 어떤 구조로 가져가야하는가?
> StateFlow
특징: 초기값을 가짐.
용도: 항상 최신 값을 필요로 하는 UI 상태 (UiState)에 적합.
비교: 기존 LiveData와 유사. <br>

> SharedFlow
특징: 이벤트 구독자가 0명일 수도, 여러 명일 수도 있음.
용도: 구독자가 없을 때 이벤트가 무시되어도 되는 경우 (UiEvent)에 적합.
특징: 이벤트가 구독자 간에 공유됨. <br>

> Channel
특징: 각각의 이벤트가 오직 하나의 구독자에게만 전달됨.
용도: 이벤트가 무시되지 않아야 하며, 구독자가 나타날 때까지 일시중지되는 경우에 적합.
특징: Hot Stream으로, SingleLiveEvent 동작을 복제하는 데 사용. <br>



## :fire: 트러블슈팅
> <h3>기존에 사용하던 코루틴 사용법과 MVI 패턴에 맞는 코루틴을 R&D 한 결과 아래와 같은 결과를 도출해냄</h3>
  
- 직접적인 CoroutineScope 사용의 문제점:
MVI 패턴에서는 일반적으로 뷰모델 또는 비즈니스 로직에서 상태를 관리하고 코루틴을 사용하는 것이 좋다.
MainActivity와 같은 UI 관련 클래스에서 직접적으로 CoroutineScope를 생성하고 사용하는 것은 코드의 유지보수성을 떨어뜨릴 수 있다.

- 명확한 상태 관리:
MVI 패턴은 상태(State)를 명확하게 관리하는 것을 중요시한다.
CoroutineScope를 직접 생성하면 상태 관리가 분산되고 혼란스러워질 수 있다.

- 수명 주기와의 연관성:
코루틴이 Activity의 수명 주기와 맞지 않게 실행될 수 있다.
이는 메모리 누수나 비정상적인 동작을 일으킬 수 있다.
예를 들어, Activity가 파괴된 후에도 코루틴이 계속 실행된다면 문제가 될 수 있다.

### 기존 사용법

```
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.Default).launch {
            Log.d(TAG, "Dispatchers.Default: ${Thread.currentThread().name}")
        }

        CoroutineScope(Dispatchers.IO).launch {
            Log.d(TAG, "Dispatchers.IO: ${Thread.currentThread().name}")
        }

        CoroutineScope(Dispatchers.Main).launch {
            Log.d(TAG, "Dispatchers.Main: ${Thread.currentThread().name}")
        }

        CoroutineScope(Dispatchers.Unconfined).launch {
            Log.d(TAG, "Dispatchers.Unconfined before: ${Thread.currentThread().name}")
            delay(2000)
            Log.d(TAG, "Dispatchers.Unconfined after: ${Thread.currentThread().name}")
        }
    }
}
```

<br>

### 새로 도입한 코루틴 사용법
<img width="456" alt="image" src="https://github.com/user-attachments/assets/efdcb88d-4853-48bc-a874-20e41188e8a1">
<img width="714" alt="image" src="https://github.com/user-attachments/assets/11c64a7a-7227-4baf-86d6-ec5fed87017b">
<img width="630" alt="image" src="https://github.com/user-attachments/assets/1542df1a-dfc2-4b01-9591-c40fbd0cc19e">
<img width="526" alt="image" src="https://github.com/user-attachments/assets/ccf1f6fa-4c1b-491a-9971-76c86ac8e353">
<img width="906" alt="image" src="https://github.com/user-attachments/assets/799002dc-667c-4ece-91c8-b7e0bd8041fc">
<img width="573" alt="image" src="https://github.com/user-attachments/assets/6edb9805-6bbb-417f-85d3-60783cc7c954">

<br>

### 결론
CoroutineDispatcherProvider를 Hilt 모듈에 주입하여 UseCase에서 사용하는 이유는 코드의 유연성과 유지보수성, 테스트 용이성을 높이기 위함입니다.<br>
이를 통해 비동기 작업을 효율적으로 관리하고, 코드의 가독성과 재사용성을 높일 수 있습니다.

- 코드 재사용성
CoroutineDispatcherProvider를 통해 다양한 UseCase에서 동일한 디스패처를 재사용할 수 있습니다. 이는 코드의 중복을 줄이고, 일관성을 유지하는 데 도움이 됩니다.

- 구조의 명확성
각 UseCase에서 어떤 디스패처를 사용할지를 명확히 할 수 있습니다. 예를 들어, IO 작업은 dispatcherIO를 사용하고, 메인 스레드 작업은 dispatcherMain을 사용하도록 명확히 구분할 수 있습니다.

<br>

## :open_file_folder: 프로젝트 구조

```markdown
src
├── app (module)
│   └── ui.theme
│       ├── type
│       ├── theme
│       ├── colors
│       └── shape
│   ├── MainActivity
│   └── MyApplication
├── core (module)
│   ├── base
│       ├── BaseActivity
│       ├── BaseFragment
│       ├── BaseViewModel
│       ├── ViewBindingListAdapter
│       └── ViewBindingViewHolder
│   ├── mvi
│       ├── MVIActivity
│       ├── MVIFragment
│       ├── MVIView
│       ├── MVIViewModel
│       ├── ViewEffect
│       ├── ViewIntent
│       └── ViewState
├── data (module)
│   ├── datasource
│       └── SearchDataSourceImpl
│   ├── di
│       ├── annotation
│           └── RetrofitAnnotation
│       ├── usecase
│           └── SearchUseCaseModule
│       ├── ApiModule
│       ├── DataSourceModule
│       ├── NetworkModule
│       ├── PersistentModule
│       ├── RepositoryModule
│       └── SystemModule
│   ├── local
│       ├── dao
│           ├── BookmarkDao
│           ├── RemoteKeyDao
│           └── UserDao
│       ├── UserDataBaseImpl
│       └── UserRoomDataBase
│   ├── mapper
│       ├── BookmarkUserEntityListMapper
│       ├── BookmarkUserEntityMapper
│       ├── Mapper
│       ├── RemoteKeyEntityMapper
│       └── UserResponseMapper
│   ├── model
│       ├── entity
│           ├── BookmarkEntity
│           ├── BookmarkUserEntity
│           ├── RemoteKeyEntity
│           └── UserEntity
│       ├── remote
│           ├── SearchUserResponse
│           └── UserResponse
│       ├── DataModel
│       ├── EntityModel
│       └── ResponseModel
│   ├── remote
│       └── SearchAPI
│   ├── repository
│       └── SearchRepositoryImpl
│   └── CoroutineDispatcherProviderImpl
├── domain (module)
│   ├── datasource
│       ├── SearchDataSource
│       └── UserDataBase
│   ├── model
│       ├── BookmarkUser
│       ├── LoadType
│       ├── PagingResult
│       ├── RemoteKey
│       └── User
│   ├── repository
│       └── SearchRepository
│   ├── usecase
│       ├── GetUserUseCase
│       ├── SearchUserUseCase
│       ├── UpdateUserBookmarkUseCase
│       └── UseCase
│   ├── CoroutineDispatcherProvider
│   ├── DomainModel
│   └── Result
└── presentation (module)
    ├── deeplink (module)
        ├── DeepLink
        └── NavigationExt
    ├── detail (module)
        ├── Detail
        ├── DetailContract
        └── DetailViewModel
    └── search (module)
        ├── ext
        ├── PagingScrollListener
        ├── Search
        ├── SearchContract
        ├── SearchViewModel
        └── UserListAdapter



```

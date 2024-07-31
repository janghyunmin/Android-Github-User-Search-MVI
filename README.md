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
    <img src="https://github.com/user-attachments/assets/ffdc3d54-33a0-4f79-ba1e-48c1ec1ee6ca" style="width: 50%; height: 100%;"/> <br>
    <img src="https://github.com/user-attachments/assets/a23c34d6-3f2e-4296-bac9-e02b36213bca" style="width: 50%; height: 100%;"/> <br>
    <img src="https://github.com/user-attachments/assets/08274702-c2a5-4476-9249-fd69e24d2f0f" style="width: 50%; height: 100%;"/> <br>
</div>

<br>


* Github 사용자 검색 기능
  * 검색한 사용자 리스트 출력
* 사용자 북마크 저장/해제 기능
* 딥링크 웹뷰 호출
* Room Database 로 로컬 데이터 저장

## 🔧 Stack
- **lib version** : kotlin 1.9.0 / ksp 2.0.10-RC-1.0.24 / dagger-hilt 2.51.1 / room = 2.6.1
- **Language** : Kotlin , xml
- **Framework** : Android Studio
- **API** : [https://api.github.com](https://api.github.com)

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

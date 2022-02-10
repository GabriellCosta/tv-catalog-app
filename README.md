## Architecture
- Here I use a mix of mvvm with [unidirectional data flow](https://proandroiddev.com/unidirectional-data-flow-on-android-the-blog-post-part-1-cadcf88c72f5)
, the idea is to centralize and understand what a feature can do

- Each feature is a different module, I choose the approach to have public/impl modules for features,
 so I can achieve a horizontal modularization scheme
    - I change on a every implementation should depended only on a public module,
    so any implementation change will trigger only the implementation module and the core module

- Each UseCase respond a monad called ResultDomain, for them I always create a success model and a
error model, so each API response could have different error's if needed and we can easily
which problem's we should handle on the UI

### Types of modules

    - Infra modules live on the infra directory, they are responsible for infrastructure of the
    project

    - Feature modules hold the logic for the features that the user will interact
        - the public feature modules usually will have navigators, so we can open the features and
        public UseCases

    - Design system module hold the logic for how our views will look, the idea is to any typography,
     color, size, spacing, icon being there, so everything will be more consistent

## Decisions

- Some decision where made due to my lake of time here I share some of them

    - DI i choose KOIN for be simples and with a easy configuration

    - Navigation framework i choose Cicerone because is a light framework and with a easy
     configuration, my decision to not choose something like Jetpack Navigation is because this one
     has to much code (and configuration files, read xml files) if want to use something like my
     public/impl feature modules

     - For async I use coroutines cuz is simple

     - I used my [github repository app](https://github.com/GabriellCosta/github-repo-view) as base for this project

     - Due to time I decided to not create the final screen where I should display the episode info,
     but I make every episode item on the movie detail screen have the information needed

     - To validate if everything is ok I use Github Action as my CI

## Improvement's

- Instead of using gradle buildSrc folder I would update to use a plugin, also move the dependencies
to the core package, so with this I can avoid things like `dependencie.Depdencies` that is bad to read.
Creating a plugin module would require more time than creating a easily buildSrc folder

- I wrote some TODOs along with the code of thing to change

- The Movie Detail public module should be a pure kotlin module, on the hurry a mae what is easier that
is convert it to a android module and move the parcelable to it, one approach I would like to
implement is add a fragment factory for my fragment and pass the parameter's as a constructor parameter
for the fragment, also I would need to test if this could lead to some problems with android life cycle

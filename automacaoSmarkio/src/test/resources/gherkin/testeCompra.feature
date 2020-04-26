Feature: Pesquisar produto pela barra de busca, acessar um produto e adiciona-lo ao carrinho


  Scenario: adicionar produto ao carrinho com sucesso
  
    Given estou na home page do site
    When pesquiso um produto na barra de busca
    And valido que a pesquisa foi feita com sucesso
    And clico em um produto
    And adiciono o produto ao carrinho
    Then valido que o produto foi adicionado com sucesso
  


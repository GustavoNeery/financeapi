<h1 align="center">Finance API</h1>
<p align="center">Gerenciamente de finanças</p>
<h4 align="center">Finance API 🚀</h4>

⚙️ Expenses API Routes  
📥 POST  
POST /expenses  
Cria um novo registro de despesa.  

📤 GET  
GET /expenses  
Retorna todas as despesas.  

GET /expenses/transactionDate  
Retorna despesas filtradas por data de transação.  

🔄 PUT  
PUT /expenses/{id}  
Atualiza uma despesa existente pelo ID.  

❌ DELETE  
DELETE /expenses/{id}  
Remove uma despesa pelo ID.

📆 Month Costs API Routes
📤 GET  
GET /MonthCosts  
Retorna o custo total por mês.

GET /MonthCosts/period  
Retorna os custos dentro de um período específico.

🔄 PUT  
PUT /MonthCosts/{id}  
Atualiza um registro de custo mensal pelo ID.

❌ DELETE  
DELETE /MonthCosts/{id}  
Remove um custo mensal pelo ID.
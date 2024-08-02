function StartPage() {
  return (
	  <>
		  <h1>Simon Erhardt's Simple Chat Application</h1>
		  <nav>
			  <ul>
				  <li>
					  <a href={"/agent"}>Agent's dashboard</a>
				  </li>
				  <li>
					  <a href={"/customer"}>Customer's entrypoint</a>
				  </li>
			  </ul>
		  </nav>
	  </>
  )
}

export default StartPage

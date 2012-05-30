class UrlMappings {

	static mappings = {
		// Used by app info plugin
		"/admin/manage/$action?"(controller: "adminManage")
		"/adminManage/$action?"(controller: "errors", action: "urlMapping")

		// standard mappings
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}


		// "/"(view:"/index")
		"/"(controller:"mainMenu", action:"index")
		"500"(view:'/error')
	}
}

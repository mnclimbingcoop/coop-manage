package coop.mnclimbing

class HidDoorEventController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 30, 100)
        [hidDoorEventInstanceList: HidDoorEvent.list(params), hidDoorEventInstanceTotal: HidDoorEvent.count()]
    }
	
    def show = {
        def hidDoorEventInstance = HidDoorEvent.get(params.id)
        if (!hidDoorEventInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'hidDoorEvent.label', default: 'HidDoorEvent'), params.id])}"
            redirect(action: "list")
        }
        else {
            [hidDoorEventInstance: hidDoorEventInstance]
        }
    }
}

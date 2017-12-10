package com.sa45.team3.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sa45.team3.model.UsageRecord;
import com.sa45.team3.repository.UsageRecordRepository;

@RequestMapping(value = "/mechanic")
@Controller
public class UsageRecordControl {

	@Resource
	UsageRecordRepository prepo;

	@RequestMapping(value = "/usage-record", method = RequestMethod.GET)
	public ModelAndView recordListPage() {

		ModelAndView mav = new ModelAndView("usage-record"); // create jsp
		List<UsageRecord> usageRecordList = prepo.findAll();
		mav.addObject("recordList", usageRecordList);
		return mav;

	}

	@RequestMapping(value = "/usage-record/create", method = RequestMethod.GET)
	public ModelAndView usageRecordAdd() {

		ModelAndView mav = new ModelAndView("usage-record-add");
		List<UsageRecord> usageRecordAdd = new ArrayList<UsageRecord>();
		mav.addObject("usageRecordAdd", usageRecordAdd);
		return mav;
		// learn how to do dropdown fields in the web page
	}

	@RequestMapping(value = "/usage-record/create", method = RequestMethod.POST)
	public ModelAndView createNewUsageRecord(@ModelAttribute UsageRecord usageRecord, BindingResult result,
			final RedirectAttributes redirectAttributes) {

		// what does bindingresult do?

		if (result.hasErrors())
			return new ModelAndView("usagerecord-add"); // return to this page if error

		ModelAndView mav = new ModelAndView();
		String message = "New usage record: " + usageRecord.getRecordID() + " was successfully created.";

		prepo.saveAndFlush(usageRecord);
		mav.setViewName("redirect:/mechanic/usage-record");

		redirectAttributes.addFlashAttribute("message", message); // what does this do?
		return mav;
	}

}
/*
 * @RequestMapping(value="/")
 * 
 * @Controller public class UsageRecordControl {
 * 
 * @Resource SupplierRepository sprepo;
 * 
 * @RequestMapping(value="/list", method= RequestMethod.GET) public ModelAndView
 * supplierListPage() {
 * 
 * ModelAndView mav = new ModelAndView("supplier-list"); //create jsp
 * List<Supplier> supplierList = sprepo.findAll(); mav.addObject("slist",
 * supplierList); return mav;
 * 
 * 
 * }
 * 
 * @RequestMapping(value="/create", method= RequestMethod.GET) public
 * ModelAndView supplierAdd() {
 * 
 * ModelAndView mav = new ModelAndView("supplier-add"); Supplier supl = new
 * Supplier(); mav.addObject("supl", supl); return mav; //learn how to do
 * dropdown fields in the web page }
 * 
 * @RequestMapping(value="/create", method=RequestMethod.POST) public
 * ModelAndView createNewUser(@ModelAttribute Supplier supplier, BindingResult
 * result, final RedirectAttributes redirectAttributes) {
 * 
 * //what does bindingresult do?
 * 
 * if (result.hasErrors()) return new ModelAndView("supplier-add"); //return to
 * this page if error
 * 
 * ModelAndView mav = new ModelAndView(); String message = "New user " +
 * supplier.getSupplierName() + " was successfully created.";
 * 
 * sprepo.saveAndFlush(supplier); mav.setViewName("redirect:/list");
 * 
 * redirectAttributes.addFlashAttribute("message", message); //what does this
 * do? return mav; }
 * 
 * //{id} and String id must match
 * 
 * @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET) public
 * ModelAndView editUserPage(@PathVariable String id) { ModelAndView mav = new
 * ModelAndView("supplier-edit"); int ID = Integer.parseInt(id); Supplier
 * supplier = sprepo.findOne(ID); mav.addObject("supl", supplier); return mav; }
 * 
 * @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST) public
 * ModelAndView editUser(@ModelAttribute @Valid Supplier supplier, BindingResult
 * result, @PathVariable String id, final RedirectAttributes redirectAttributes)
 * {
 * 
 * if (result.hasErrors()) return new ModelAndView("supplier-edit");
 * 
 * ModelAndView mav = new ModelAndView("redirect:/list"); String message =
 * "Supplier was successfully updated.";
 * 
 * sprepo.saveAndFlush(supplier);
 * 
 * redirectAttributes.addFlashAttribute("message", message); return mav; }
 */

// }
